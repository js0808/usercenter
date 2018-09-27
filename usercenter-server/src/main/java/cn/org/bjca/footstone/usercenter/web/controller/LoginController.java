package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.LoginFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginCertRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.LoginResponse;
import cn.org.bjca.footstone.usercenter.biz.LoginService;
import cn.org.bjca.footstone.usercenter.vo.BizResultVo;
import org.apache.commons.lang3.tuple.Pair;
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
    return ReturnResult.error(result.getLeft().getCode());
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
  public ReturnResult<Void> logout(@RequestParam Long uid, @RequestParam String token) {
    loginService.logout(uid, token);
    return ReturnResult.success("OK");
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
