package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.USER_IS_LOCKED;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.USER_OR_PWD_ERROR;

import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.LoginResponse;
import cn.org.bjca.footstone.usercenter.config.AccountLoginConfig;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.MessageDigestUtils;
import cn.org.bjca.footstone.usercenter.util.PwdUtil;
import cn.org.bjca.footstone.usercenter.util.RDate;
import cn.org.bjca.footstone.usercenter.vo.BizResultVo;
import cn.org.bjca.footstone.usercenter.vo.LoginTokenVo;
import com.google.common.base.Joiner;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Slf4j
@Service
public class LoginService {

  @Autowired
  private AuthCodeService authCodeService;

  @Autowired
  private AccountInfoService accountInfoService;

  @Autowired
  private AccountAttemptsService accountAttemptsService;

  @Autowired
  private TokenStoreService tokenStoreService;

  @Autowired
  private AccountLoginConfig accountLoginConfig;

  public Pair<BizResultVo, LoginResponse> loginWithPassword(LoginRequest loginRequest) {
    if (StringUtils.isBlank(loginRequest.getPassword()) && StringUtils
        .isBlank(loginRequest.getAuthcode())) {
      throw new BjcaBizException(ReturnCodeEnum.REQ_PARAM_ERR, "密码或验证码至少有一项必填");
    }
    String username = loginRequest.getUsername();
    log.debug("登录:{}", username);
    AccountInfo accountInfo = accountInfoService.findAccountInfoByAccount(username);
    // 账号不存在
    if (accountInfo == null) {
      return Pair.of(BizResultVo.of(false, USER_OR_PWD_ERROR), null);
    }
    // 密码
    if (StringUtils.isNotBlank(loginRequest.getPassword())) {
      BizResultVo bizResultVo = passwordValid(loginRequest, accountInfo);
      if (!bizResultVo.isSuccess()) {
        return Pair.of(bizResultVo, null);
      }
    }
    // 验证码
    if (StringUtils.isNotBlank(loginRequest.getAuthcode())) {
      authCodeValid(loginRequest, accountInfo);
    }
    LoginResponse response = buildLoginResponse(loginRequest, accountInfo);
    return Pair.of(BizResultVo.of(true), response);
  }

  private LoginResponse buildLoginResponse(LoginRequest loginRequest, AccountInfo accountInfo) {
    String account = accountInfo.getAccount();
    String channel = loginRequest.getChannel();
    String tokenSrc = Joiner.on("_").join(account, channel, RDate.getNow().getTime());
    String token = MessageDigestUtils.md5Hex(tokenSrc);
    int expire = loginRequest.getExpireMinutes() != 0 ? loginRequest.getExpireMinutes()
        : accountLoginConfig.getTokenExpireMinutes();
    Date expireDate = RDate.add(new Date(), Calendar.MINUTE, expire);
    tokenStoreService.store(LoginTokenVo.of(account, token, expireDate.getTime(), expire));
    LoginResponse response = new LoginResponse(token, expireDate.getTime());
    return response;
  }

  private BizResultVo passwordValid(LoginRequest loginRequest, AccountInfo accountInfo) {
    // 账号被锁定
    if (accountInfo.getIsLocked() && accountInfo.getLockedExpireTime()
        .after(new Date())) {
      return BizResultVo.of(false, USER_IS_LOCKED);
    }
    // 密码不正确
    String encode = PwdUtil.cipher(loginRequest.getPassword());
    boolean matches = PwdUtil.verify(accountInfo.getPassword(), encode);
    if (!matches) {
      accountAttemptsService.updateFailAttempts(loginRequest.getUsername());
      return BizResultVo.of(false, USER_OR_PWD_ERROR);
    }
    return BizResultVo.of(true);
  }

  private void authCodeValid(LoginRequest loginRequest, AccountInfo accountInfo) {
    AuthCodeValidateRequest authCodeValidateRequest = new AuthCodeValidateRequest();
    try {
      authCodeValidateRequest.setUserName(loginRequest.getUsername());
      authCodeValidateRequest.setAuthCode(loginRequest.getAuthcode());
      authCodeValidateRequest.setType(AuthCodeTypeEnum.LOGIN.value());
      authCodeService.validate(authCodeValidateRequest);
    } catch (Exception e) {
      throw new BjcaBizException(ReturnCodeEnum.AUTH_CODE_VALIDATE_ERROR);
    }
  }
}