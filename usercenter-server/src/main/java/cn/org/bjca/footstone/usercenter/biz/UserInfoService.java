package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_NOT_EXIST;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_PARAM_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_TYPE_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.SQL_EXCEPTION;
import static cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum.USER;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoQueryVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoSimpleVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoStatusVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryUserInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameChecker;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoExample;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import com.google.common.base.Strings;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
  private AccountInfoMapper accountInfoMapper;

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

    doVerify(verify, userInfo);

    int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
    if (count != 1) {
      log.error("变更实名认证信息异常{}", userInfo);
      throw new BaseException(SQL_EXCEPTION);
    }

    return buildRsp(userInfo);
  }

  private RealNameVerify getVerify(UserInfoVo userInfoVo) {
    RealNameVerify verify = checker.getVerify(userInfoVo.getRealNameType());
    if (isNull(verify)) {
      throw new BaseException(REALNAME_TYPE_ERROR);
    }
    verify.setUserInfoVo(userInfoVo);
    Pair<Boolean, String> pass = verify.checkRequest();
    log.info("校验基本信息{}", pass);
    if (!pass.getKey()) {
      throw new BaseException(REALNAME_PARAM_ERROR, pass.getValue());
    }
    return verify;
  }

  private UserInfoResponse buildRsp(UserInfo userInfo) {
    UserInfoResponse response = new UserInfoResponse();
    response.setUid(userInfo.getUid());
    return response;
  }

  /**
   * 实名认证
   */
  private void doVerify(RealNameVerify verify, UserInfo userInfo) {
    verirfy(verify, userInfo.getRealNameType());
    userInfo.setRealNameFlag(1);
  }

  private UserInfo createUserInfo() {
    UserInfo userInfo = new UserInfo();
    userInfo.setUid(SnowFlake.next());
    return userInfo;
  }

  /**
   * 变更用户信息，需要重新走实名认证
   */
  public UserInfoResponse modUser(Long uid, UserInfoVo userInfo) {
    log.info("请求实名认证变更{}", userInfo);
    RealNameVerify verify = getVerify(userInfo);

    UserInfo old = getUserInfoWithUid(uid);
    if (isNull(old)) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }

    USERINFO_COPIER.copy(userInfo, old, null);

    verirfy(verify, userInfo.getRealNameType());

    old.setRealNameType(old.getRealNameType() + "," + userInfo.getRealNameType());
    old.setUpdateTime(new Date());
    old.setVersion(old.getVersion() + 1);

    int count = userInfoMapper.updateByPrimaryKeySelective(old);
    if (count != 1) {
      log.error("变更实名认证信息异常{}", userInfo);
      throw new BaseException(SQL_EXCEPTION);
    }
    return buildRsp(old);
  }

  private void verirfy(RealNameVerify verify, String realNameType) {
    MetricsClient metricsClient = MetricsClient
        .newInstance("依赖第三方服务", "身份核实服务", realNameType);
    try {
      verify.verify();
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("实名验证失败", e);
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  public QueryUserInfoResponse getUser(Long uid) {
    UserInfo userInfo = getUserInfoWithUid(uid);
    if (nonNull(userInfo)) {
      QueryUserInfoResponse rsp = new QueryUserInfoResponse();
      USERINFO2RESPONSE.copy(userInfo, rsp, null);
      return rsp;
    }
    return null;
  }

  private UserInfo getUserInfoWithUid(Long uid) {
    UserInfoExample example = new UserInfoExample();
    example.createCriteria().andUidEqualTo(uid);
    List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
    return userInfos.isEmpty() ? null : userInfos.get(0);
  }

  public UserInfoResponse modUserSimple(Long uid, UserInfoSimpleVo userInfoSimpleVo) {
    UserInfo old = getUserInfoWithUid(uid);
    if (isNull(old)) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    UserInfo userInfo = new UserInfo();
    userInfo.setId(old.getId());
    userInfo.setEmail(userInfoSimpleVo.getEmail());
    userInfo.setHeadImgUrl(userInfoSimpleVo.getHeadImgUrl());
    userInfo.setVersion(old.getVersion() + 1);
    int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
    if (count != 1) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    return buildRsp(old);
  }

  public UserInfoResponse modUserStatus(Long uid, UserInfoStatusVo userInfoStatusVo) {
    UserInfo old = getUserInfoWithUid(uid);
    if (isNull(old)) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    UserInfo userInfo = new UserInfo();
    userInfo.setId(old.getId());
    userInfo.setStatus(userInfoStatusVo.getStatus().toString());
    userInfo.setVersion(old.getVersion() + 1);

    int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
    if (count != 1) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    return buildRsp(old);
  }

  public QueryUserInfoResponse getUserByAccount(UserInfoQueryVo queryVo) {
    String account = queryVo.getAccount();
    if (Strings.isNullOrEmpty(account)) {
      return null;
    }
    Long uid = getUidFromAccount(account);
    if (isNull(uid)) {
      return null;
    }
    return getUser(uid);
  }

  private Long getUidFromAccount(String account) {
    AccountInfoExample example = new AccountInfoExample();
    example.createCriteria().andAccountEqualTo(account);
    List<AccountInfo> accountInfos = accountInfoMapper.selectByExample(example);
    if (accountInfos.isEmpty()) {

      return null;
    }
    AccountInfo accountInfo = accountInfos.get(0);
    if (!Objects.equals(USER.toString(), accountInfo.getUserType())) {
      return null;
    }
    return accountInfo.getUid();
  }
}
