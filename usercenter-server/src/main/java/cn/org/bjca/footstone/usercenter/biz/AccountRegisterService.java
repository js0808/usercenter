package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.enmus.AccountTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountChangeRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountRegisterRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountStatusUpdateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ModifyPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ResetPasswordRequest;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.PwdUtil;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

  public void accountRegister(AccountRegisterRequest request) throws Exception {
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
    info.setAccount(request.getAccount());
    info.setPassword(password);
    info.setVersion(1);
    info.setCreateTime(new Date());
    info.setUpdateTime(new Date());
    accountInfoMapper.insertSelective(info);
  }

  public void resetPassword(ResetPasswordRequest request) throws Exception {
    /**检查帐号是否存在**/
    AccountInfo info = accountInfoService.findAccountInfoByAccount(request.getAccount());
    if (info == null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
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
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(request.getAccount());
    if (accountInfo == null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
    /**变更帐号状态**/
    accountInfo.setAccount(request.getStatus());
    accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
  }

  public void modifyPassword(ModifyPasswordRequest request) throws Exception {
    AccountInfo accountInfo = accountExit(request.getAccount());
    if (accountInfo.getIsLocked() && accountInfo.getLockedExpireTime()
        .after(new Date())) {
      throw new BjcaBizException(ReturnCodeEnum.USER_IS_LOCKED);
    }
    /**验证原密码**/
    boolean matches = PwdUtil.verify(accountInfo.getPassword(), request.getOldPassword());
    if (!matches) {
      throw new BjcaBizException(ReturnCodeEnum.USER_OR_PWD_ERROR);
    }
    accountInfo.setPassword(PwdUtil.cipher(request.getNewPassword()));
    accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
  }

  public void accountChange(AccountChangeRequest request) throws Exception {
    AccountInfo oldaccountInfo = accountExit(request.getOldAccount());
    /**检查待变更帐号是否已存在**/
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(request.getAccount());
    if (accountInfo != null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_EXIT_ERROR);
    }
    /**检查验证标示是否存在**/
    String key = authCodeService.getValidate_key() + request.getType() + request.getOldAccount();
    String validateId = stringRedisTemplate.opsForValue().get(key);
    if (StringUtils.isEmpty(validateId)) {
      throw new BjcaBizException(ReturnCodeEnum.VALIDATE_ID_NOT_EXIT_ERROR);
    }
    if (StringUtils.equals(request.getValidateId(), validateId)) {
      throw new BjcaBizException(ReturnCodeEnum.VALIDATE_ID_NOT_EXIT_ERROR);
    }
    AuthCodeValidateRequest validateRequest = new AuthCodeValidateRequest();
    validateRequest.setAuthCode(request.getAuthCode());
    validateRequest.setUserName(request.getAccount());
    validateRequest.setType(AuthCodeTypeEnum.CHANGE.value());
    authCodeService.validate(validateRequest);

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
}
