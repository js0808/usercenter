package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.enmus.AccountStatusEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.AccountTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountChangeRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountRegisterRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountStatusUpdateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ModifyPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.RegisterCertRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ResetPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountCheckResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountInfoCheckResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountRegisterResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.CertRegisterResponse;
import cn.org.bjca.footstone.usercenter.biz.signverify.VerifySignCertService;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.PwdUtil;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import java.util.Date;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AccountRegisterService {

  @Autowired
  private AccountInfoMapper accountInfoMapper;
  @Autowired
  private AuthCodeService authCodeService;
  @Autowired
  private AccountInfoService accountInfoService;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  @Autowired
  private AccountAttemptsService accountAttemptsService;
  @Autowired
  private VerifySignCertService verifySignAndCert;

  public AccountRegisterResponse accountRegister(AccountRegisterRequest request) throws Exception {
    /**检查帐号是否存在**/
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(request.getAccount());
    if (accountInfo != null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_EXIT_ERROR);
    }
    /**验证码验证**/
    AuthCodeValidateRequest validateRequest = new AuthCodeValidateRequest();
    validateRequest.setUserName(request.getAccount());
    validateRequest.setAuthCode(request.getAuthCode());
    validateRequest.setType(AuthCodeTypeEnum.REGIST.value());
    authCodeService.validate(validateRequest);
    /**密码处理**/
    String password = PwdUtil.cipher(request.getPassword());
    /**添加帐号信息**/
    AccountInfo info = new AccountInfo();
    if (authCodeService.isEmail(request.getAccount())) {
      info.setAccountType(AccountTypeEnum.EMAIL.value());
    } else {
      info.setAccountType(AccountTypeEnum.MOBILE.value());
    }
    Long uid = SnowFlake.next();
    info.setUid(uid);
    info.setAccount(request.getAccount());
    info.setPassword(password);
    info.setVersion(1);
    info.setAppId(request.getAppId());
    info.setCreateTime(new Date());
    info.setUpdateTime(new Date());
    accountInfoMapper.insertSelective(info);
    AccountRegisterResponse response = new AccountRegisterResponse();
    response.setUid(uid);
    return response;
  }

  public void resetPassword(ResetPasswordRequest request) throws Exception {
    /**检查帐号是否存在**/
    AccountInfo info = accountInfoService.findAccountInfoByAccount(request.getAccount());
    if (info == null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
    chechCertAccount(info);
    /**验证码验证**/
    AuthCodeValidateRequest validateRequest = new AuthCodeValidateRequest();
    validateRequest.setUserName(request.getAccount());
    validateRequest.setAuthCode(request.getAuthCode());
    validateRequest.setType(AuthCodeTypeEnum.RESET.value());
    authCodeService.validate(validateRequest);

    String password = PwdUtil.cipher(request.getPassword());
    info.setPassword(password);
    /**强制解锁当前帐号**/
    info.setIsLocked(false);
    accountInfoMapper.updateByPrimaryKeySelective(info);
  }

  public void accountStatus(AccountStatusUpdateRequest request) throws Exception {
    AccountInfo accountInfo = accountExit(request.getAccount());
    checkAccountStatus(accountInfo);
    /**变更帐号状态**/
    accountInfo.setStatus(request.getStatus());
    accountInfo.setUpdateTime(new Date());
    accountInfo.setVersion(accountInfo.getVersion() + 1);
    accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
  }

  private void checkAccountStatus(AccountInfo accountInfo) {
    if (StringUtils.equals(accountInfo.getStatus(), AccountStatusEnum.INVALID.value())) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_STATUS_ERROR);
    }
  }

  private void chechCertAccount(AccountInfo accountInfo) {
    if (StringUtils.equals(accountInfo.getAccountType(), AccountTypeEnum.CERT.value())) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_CERT_NO_PWD_ERROR);
    }
  }

  public void modifyPassword(ModifyPasswordRequest request) throws Exception {
    if (StringUtils.equals(request.getNewPassword(), request.getOldPassword())) {
      throw new BjcaBizException(ReturnCodeEnum.OLD_NEW_PWD_EQUALS_ERROR);
    }
    AccountInfo accountInfo = accountExit(request.getAccount());
    if (accountInfo.getIsLocked() && accountInfo.getLockedExpireTime()
        .after(new Date())) {
      throw new BjcaBizException(ReturnCodeEnum.USER_IS_LOCKED);
    }
    chechCertAccount(accountInfo);

    /**验证原密码**/
    boolean matches = PwdUtil.verify(accountInfo.getPassword(), request.getOldPassword());
    if (!matches) {
      try {
        accountAttemptsService.updateFailAttempts(request.getAccount());
      } catch (LockedException e) {
        throw new BjcaBizException(ReturnCodeEnum.USER_IS_LOCKED);
      }
      throw new BjcaBizException(ReturnCodeEnum.USER_OR_PWD_ERROR);
    }
    accountInfo.setPassword(PwdUtil.cipher(request.getNewPassword()));
    accountInfoMapper.updateByPrimaryKeySelective(accountInfo);

    accountAttemptsService.resetFailAttempts(request.getAccount());
  }

  public void accountChange(AccountChangeRequest request) throws Exception {
    AccountInfo oldaccountInfo = accountExit(request.getOldAccount());
    /**检查待变更帐号是否已存在**/
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(request.getAccount());
    if (accountInfo != null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_EXIT_ERROR);
    }
    /**检查验证标示是否存在**/
    String key =
        authCodeService.getValidateKey() + request.getType() + "_" + request.getOldAccount();
    String validateId = stringRedisTemplate.opsForValue().get(key);
    if (StringUtils.isEmpty(validateId)) {
      throw new BjcaBizException(ReturnCodeEnum.VALIDATE_ID_NOT_EXIT_ERROR);
    }
    if (!StringUtils.equals(request.getValidateId(), validateId)) {
      throw new BjcaBizException(ReturnCodeEnum.VALIDATE_ID_NOT_EXIT_ERROR);
    }
    stringRedisTemplate.delete(key);
    AuthCodeValidateRequest validateRequest = new AuthCodeValidateRequest();
    validateRequest.setAuthCode(request.getAuthCode());
    validateRequest.setUserName(request.getAccount());
    validateRequest.setType(AuthCodeTypeEnum.CHANGE.value());
    authCodeService.validate(validateRequest);

    if (authCodeService.isEmail(request.getAccount())) {
      oldaccountInfo.setAccountType(AccountTypeEnum.EMAIL.value());
    } else {
      oldaccountInfo.setAccountType(AccountTypeEnum.MOBILE.value());
    }
    oldaccountInfo.setAccount(request.getAccount());
    oldaccountInfo.setUpdateTime(new Date());
    oldaccountInfo.setVersion(oldaccountInfo.getVersion() + 1);
    accountInfoMapper.updateByPrimaryKeySelective(oldaccountInfo);
  }

  public AccountInfo accountExit(String account) {
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(account);
    if (accountInfo == null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
    return accountInfo;
  }

  /**
   * 证书注册
   */
  public CertRegisterResponse registerByCert(RegisterCertRequest req) {
    //验证签名和证书
    verifySignAndCert.verifySignAndCert(req.getAppId(), req.getSign(), req.getSource(),
        req.getUserCert(), req.getSignAlgIdentifier(), req.getCertPolicyId());
    //获取证书唯一标识
    String certId = verifySignAndCert.getCertUid(req.getUserCert(), req.getAppId());
    //检查证书是否注册过
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(certId);
    if (accountInfo != null) {
      throw new BjcaBizException(ReturnCodeEnum.CERT_EXIST_ERROR);
    }
    //保存证书账号
    AccountInfo info = new AccountInfo();
    info.setAccountType(AccountTypeEnum.CERT.value());
    info.setAccount(certId);
    info.setAppId(req.getAppId());
    Long uid = SnowFlake.next();
    info.setUid(uid);
    accountInfoMapper.insertSelective(info);
    return CertRegisterResponse.builder().uid(uid).account(certId).build();
  }

  public AccountCheckResponse accountInfo(String account) {
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(account);
    if (Objects.isNull(accountInfo)) {
      return AccountCheckResponse.of(false, null, null);
    }

    final AccountInfoCheckResponse accountInfoResponse = new AccountInfoCheckResponse();
    BeanUtils.copyProperties(accountInfo, accountInfoResponse);
    return AccountCheckResponse.of(true, accountInfo.getAccount(), accountInfoResponse);
  }
}
