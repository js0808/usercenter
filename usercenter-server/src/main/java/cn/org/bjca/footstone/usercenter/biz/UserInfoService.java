package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_NOT_EXIST;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_PARAM_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_TYPE_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.SQL_EXCEPTION;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoSimpleVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryUserInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameChecker;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/13
 * @since 1.0
 */
@Slf4j
@Service
public class UserInfoService {

  private static final BeanCopier USERINFO_COPIER = BeanCopier
      .create(UserInfoVo.class, UserInfo.class, false);

  private static final BeanCopier USERINFO2RESPONSE = BeanCopier
      .create(UserInfo.class, QueryUserInfoResponse.class, false);
  @Autowired
  private UserInfoMapper userInfoMapper;

  @Autowired
  private RealNameChecker checker;

  public UserInfoResponse addUser(UserInfoVo userInfoVo) {
    log.info("请求实名认证{}", userInfoVo);
    RealNameVerify verify = getVerify(userInfoVo);

    UserInfo userInfo = createUserInfo();
    USERINFO_COPIER.copy(userInfoVo, userInfo, null);

    int row = userInfoMapper.insertSelective(userInfo);
    if (row != 1) {
      log.error("插入用户表异常{}", userInfo);
      throw new BaseException(SQL_EXCEPTION);
    }

    doVerify(userInfo);

    userInfoMapper.updateByPrimaryKeySelective(userInfo);

    return buildRsp(userInfo);
  }

  private RealNameVerify getVerify(UserInfoVo userInfoVo) {
    RealNameVerify verify = checker.getVerify(userInfoVo.getRealNameType());
    if (isNull(verify)) {
      throw new BaseException(REALNAME_TYPE_ERROR);
    }
    verify.setUserInfoVo(userInfoVo);
    Pair<Boolean, String> pass = verify.checkRequest();
    if (!pass.getKey()) {
      throw new BaseException(REALNAME_PARAM_ERROR, pass.getValue());
    }
    return verify;
  }

  private UserInfoResponse buildRsp(UserInfo userInfo) {
    UserInfoResponse response = new UserInfoResponse();
    response.setUid(userInfo.getId());
    return response;
  }

  /**
   * 实名认证
   */
  private void doVerify(UserInfo userInfo) {
    userInfo.setRealNameFlag(1);
  }

  private UserInfo createUserInfo() {
    return new UserInfo();
  }

  /**
   * 变更用户信息，需要重新走实名认证
   */
  public UserInfoResponse modUser(Integer uid, UserInfoVo userInfo) {
    log.info("请求实名认证变更{}", userInfo);
    RealNameVerify verify = getVerify(userInfo);

    UserInfo old = userInfoMapper.selectByPrimaryKey(uid);
    if (isNull(old)) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }

    USERINFO_COPIER.copy(userInfo, old, null);

    old.setRealNameType(old.getRealNameType() + "," + userInfo.getRealNameType());
    old.setUpdateTime(new Date());
    old.setVersion(old.getVersion() + 1);
    //TODO 状态变更为？

    userInfoMapper.updateByPrimaryKeySelective(old);
    return buildRsp(old);
  }

  public QueryUserInfoResponse getUser(Integer uid) {
    UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uid);
    if (nonNull(userInfo)) {
      QueryUserInfoResponse rsp = new QueryUserInfoResponse();
      USERINFO2RESPONSE.copy(userInfo, rsp, null);
      //noinspection unchecked
      return rsp;
    }
    return null;
  }

  public UserInfoResponse modUserSimple(Integer uid, UserInfoSimpleVo userInfoSimpleVo) {
    UserInfo old = userInfoMapper.selectByPrimaryKey(uid);
    if (isNull(old)) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    UserInfo userInfo = new UserInfo();
    userInfo.setId(uid);
    userInfo.setEmail(userInfoSimpleVo.getEmail());
    userInfo.setHeadImgUrl(userInfoSimpleVo.getHeadImgUrl());
    userInfo.setVersion(old.getVersion() + 1);
    int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
    if (count != 1) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    return buildRsp(userInfo);
  }
}
