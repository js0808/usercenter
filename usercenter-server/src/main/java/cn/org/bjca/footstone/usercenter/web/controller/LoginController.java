package cn.org.bjca.footstone.usercenter.web.controller;

import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.USER_TOKEN_WRONG;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.LoginFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginCertRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.LogouRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.LoginResponse;
import cn.org.bjca.footstone.usercenter.biz.LoginService;
import cn.org.bjca.footstone.usercenter.vo.BizResultVo;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录管理
 *
 * @author LvYong
 * @create 2018/8/15
 **/
@Validated
@RestController
public class LoginController implements LoginFacade {

  @Autowired
  private LoginService loginService;

  @Override
  public ReturnResult<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
    Pair<BizResultVo, LoginResponse> result = loginService.loginWithPassword(request);
    if (result.getLeft().isSuccess()) {
      return ReturnResult.success(result.getRight());
    }
    return ReturnResult.error(result.getLeft().getCode(), result.getRight());
  }

  @Override
  public ReturnResult<LoginResponse> loginByCert(@Validated @RequestBody LoginCertRequest request) {
    Pair<BizResultVo, LoginResponse> result = loginService.loginWithCert(request);
    if (result.getLeft().isSuccess()) {
      return ReturnResult.success(result.getRight());
    }
    return ReturnResult.error(result.getLeft().getCode());
  }

  @Override
  public ReturnResult<Void> logout(@Validated @RequestBody LogouRequest request) {
    BizResultVo bizResultVo = loginService.logout(request.getUid(), request.getToken());
    return bizResultVo.isSuccess() ? ReturnResult.success("OK")
        : ReturnResult.error(USER_TOKEN_WRONG);
  }

  @Override
  public ReturnResult<AccountInfoResponse> tokenInfo(@RequestParam Long uid,
      @RequestParam String token) {
    Pair<BizResultVo, AccountInfoResponse> result = loginService.tokenInfo(uid, token);
    if (result.getLeft().isSuccess()) {
      return ReturnResult.success(result.getRight());
    }
    return ReturnResult.error(result.getLeft().getCode());
  }
}
