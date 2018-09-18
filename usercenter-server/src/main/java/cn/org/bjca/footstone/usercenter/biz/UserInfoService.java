package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_NOT_EXIST;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_PARAM_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_TYPE_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REQ_PARAM_ERR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.RESOURCE_NOT_EXIST;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.SQL_EXCEPTION;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.USER_STATUS_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum.INVALID;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.enmus.NotifyTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoQueryVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoSimpleVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoStatusVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryUserInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameChecker;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.NotifyInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoHistoryMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapperCustom;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.NotifyInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoAccountJoin;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoHistory;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.vo.NotifyInfoDataVo;
import cn.org.bjca.footstone.usercenter.vo.NotifyInfoVo;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

  private static final BeanCopier USERINFO2HISTORY = BeanCopier
      .create(UserInfo.class, UserInfoHistory.class, false);
  @Autowired
  private DataSourceTransactionManager transactionManager;

  @Autowired
  private UserInfoMapper userInfoMapper;
  @Autowired
  private UserInfoHistoryMapper historyMapper;
  @Autowired
  private AccountInfoMapper accountInfoMapper;
  @Autowired
  private RealNameChecker checker;
  @Autowired
  private NotifyInfoMapper notifyInfoMapper;
  @Autowired
  private AccountInfoService accountInfoService;
  @Autowired
  private UserInfoMapperCustom userInfoMapperCustom;

  public UserInfoResponse addUser(UserInfoVo userInfoVo) {
    log.info("请求实名认证{}", userInfoVo);

    RealNameVerify verify = getVerify(userInfoVo);
    AccountInfo account = accountInfoService.findAccountInfoByUid(userInfoVo.getUid());
    if (isNull(account)) {
      throw new BaseException(ACCOUNT_NOT_EXIT_ERROR);
    }

    UserInfo userInfo = createUserInfo();
    USERINFO_COPIER.copy(userInfoVo, userInfo, null);

    int row = userInfoMapper.insertSelective(userInfo);
    if (row != 1) {
      log.error("插入用户表异常{}", userInfo);
      throw new BaseException(SQL_EXCEPTION);
    }

    doVerify(verify, userInfo);

//    doUpdate(null, userInfo);
    updateInfo(userInfo, account);

    return buildRsp(userInfoVo.getUid());
  }

  private void updateInfo(UserInfo userInfo, AccountInfo account) {
    TransactionStatus transaction = transactionManager
        .getTransaction(new DefaultTransactionDefinition());
    try {
      int count = userInfoMapper.updateByPrimaryKeySelective(userInfo);
      doTerminal(count);
      AccountInfo info = new AccountInfo();
      info.setId(account.getId());
      info.setRealnameId(userInfo.getId());
      count = accountInfoMapper.updateByPrimaryKeySelective(info);
      doTerminal(count);

      transactionManager.commit(transaction);
    } catch (Throwable e) {
      log.error("插入数据库异常", e);
      transactionManager.rollback(transaction);
    }
  }

  private void doTerminal(int count) {
    if (count != 1) {
      log.error("变更实名认证信息异常 期待变更1条 实际{}条", count);
      throw new BaseException(SQL_EXCEPTION);
    }
  }


  private RealNameVerify getVerify(UserInfoVo userInfoVo) {
    RealNameVerify verify = checker.getVerify(userInfoVo.getRealNameType());
    if (isNull(verify)) {
      throw new BaseException(REALNAME_TYPE_ERROR);
    }
    if (isNull(userInfoVo.getUid())) {
      throw new BaseException(REQ_PARAM_ERR);
    }

    verify.setUserInfoVo(userInfoVo);
    Pair<Boolean, String> pass = verify.checkRequest();
    log.info("校验基本信息{}", pass);
    if (!pass.getKey()) {
      throw new BaseException(REALNAME_PARAM_ERROR, pass.getValue());
    }
    return verify;
  }

  private UserInfoResponse buildRsp(Long uid) {
    UserInfoResponse response = new UserInfoResponse();
    response.setUid(uid);
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
    return new UserInfo();
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

    validateStatus(old);
    UserInfo info = new UserInfo();

    USERINFO_COPIER.copy(userInfo, info, null);

    verirfy(verify, userInfo.getRealNameType());

    info.setId(old.getId());
    info.setRealNameType(old.getRealNameType() + "," + userInfo.getRealNameType());
    info.setUpdateTime(new Date());
    info.setVersion(old.getVersion() + 1);

    doUpdate(old, info);

    return buildRsp(uid);
  }

  private void validateStatus(UserInfo old) {
    if (Objects.equals(INVALID.toString(), old.getStatus())) {
      throw new BaseException(USER_STATUS_ERROR);
    }
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
      rsp.setUid(uid);
      return rsp;
    }
    throw new BaseException(RESOURCE_NOT_EXIST);
  }

  private UserInfo getUserInfoWithUid(Long uid) {
    return userInfoMapperCustom.selectByUid(uid);
  }

  public UserInfoResponse modUserSimple(Long uid, UserInfoSimpleVo userInfoSimpleVo) {
    UserInfo old = getUserInfoWithUid(uid);
    if (isNull(old)) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    validateStatus(old);

    UserInfo userInfo = new UserInfo();
    userInfo.setId(old.getId());
    userInfo.setEmail(userInfoSimpleVo.getEmail());
    userInfo.setHeadImgUrl(userInfoSimpleVo.getHeadImgUrl());
    userInfo.setOper(userInfoSimpleVo.getOper());
    userInfo.setVersion(old.getVersion() + 1);
    doUpdate(old, userInfo);

    return buildRsp(uid);
  }

  private void doUpdate(UserInfo userInfoOld, UserInfo userInfoNew) {

    TransactionStatus transaction = transactionManager
        .getTransaction(new DefaultTransactionDefinition());
    try {
      //历史记录
      if (nonNull(userInfoOld)) {
        UserInfoHistory history = new UserInfoHistory();
        USERINFO2HISTORY.copy(userInfoOld, history, null);
        history.setRealnameId(userInfoOld.getId());
        history.setId(null);
        historyMapper.insertSelective(history);
      }
      //通知
      NotifyInfo notifyInfo = createNotify(userInfoOld, userInfoNew);
      notifyInfoMapper.insertSelective(notifyInfo);
      userInfoMapper.updateByPrimaryKeySelective(userInfoNew);

      transactionManager.commit(transaction);
    } catch (Throwable e) {
      log.error("插入数据库异常", e);
      transactionManager.rollback(transaction);
    }
  }

  private NotifyInfo createNotify(UserInfo old, UserInfo userInfo) {
    Long uid = findUid(userInfo);

    NotifyInfoDataVo notifyInfoDataVo = NotifyInfoDataVo.builder().uid(uid)
        .userType(UserTypeEnum.USER.name()).build();
    NotifyInfoVo notifyInfoVo = NotifyInfoVo.builder().data(notifyInfoDataVo)
        .type(isNull(old) ? NotifyTypeEnum.NEW.name() : NotifyTypeEnum.UPDATE.name())
        .timestamp(new Date()).build();

    NotifyInfo notifyInfo = new NotifyInfo();
    notifyInfo.setUid(uid);
    notifyInfo.setUserType(UserTypeEnum.USER.name());
    notifyInfo.setNotifyType(notifyInfoVo.getType());
    notifyInfo.setNotifyMsg(JSONObject.toJSONString(notifyInfoVo));
    return notifyInfo;
  }

  private Long findUid(UserInfo userInfo) {
    AccountInfoExample example = new AccountInfoExample();
    example.createCriteria().andRealnameIdEqualTo(userInfo.getId());
    List<AccountInfo> accountInfos = accountInfoMapper.selectByExample(example);
    return accountInfos.isEmpty() ? null : accountInfos.get(0).getUid();
  }

  public UserInfoResponse modUserStatus(Long uid, UserInfoStatusVo userInfoStatusVo) {
    UserInfo old = getUserInfoWithUid(uid);
    if (isNull(old)) {
      throw new BaseException(REALNAME_NOT_EXIST, uid);
    }
    validateStatus(old);

    UserInfo userInfo = new UserInfo();
    userInfo.setId(old.getId());
    userInfo.setOper(userInfoStatusVo.getOper());
    userInfo.setStatus(userInfoStatusVo.getStatus().toString());
    userInfo.setVersion(old.getVersion() + 1);

    doUpdate(old, userInfo);

    return buildRsp(uid);
  }

  public QueryUserInfoResponse getUserByAccount(UserInfoQueryVo queryVo) {
    String account = queryVo.getAccount();
    if (Strings.isNullOrEmpty(account)) {
      return null;
    }
    QueryUserInfoResponse rsp = getUidFromAccount(account);
    if (isNull(rsp)) {
      throw new BaseException(RESOURCE_NOT_EXIST);
    }
    return rsp;
  }

  private QueryUserInfoResponse getUidFromAccount(String account) {
    UserInfoAccountJoin userInfo = userInfoMapperCustom.selectByAccount(account);
    if (isNull(userInfo)) {
      return null;
    }
    QueryUserInfoResponse rsp = new QueryUserInfoResponse();
    USERINFO2RESPONSE.copy(userInfo, rsp, null);
    return rsp;
  }
}
