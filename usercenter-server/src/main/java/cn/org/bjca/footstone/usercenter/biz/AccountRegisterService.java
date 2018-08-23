package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.enmus.AccountTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountRegisterRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountStatusUpdateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ModifyPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ResetPasswordRequest;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.PwdUtil;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    if (authCodeService.isMobile(request.getAccount())) {
      info.setAccountType(AccountTypeEnum.MOBILE.value());
    } else {
      info.setAccountType(AccountTypeEnum.EMAIL.value());
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
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(request.getAccount());
    if (accountInfo == null) {
      throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
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
}
