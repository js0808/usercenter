package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.LoginFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.LoginResponse;
import cn.org.bjca.footstone.usercenter.biz.LoginService;
import cn.org.bjca.footstone.usercenter.vo.BizResultVo;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录管理
 *
 * @author LvYong
 * @create 2018/8/15
 **/
@Validated
@RestController
@RequestMapping
public class LoginController implements LoginFacade {

  @Autowired
  private LoginService loginService;

  @RequestMapping
  public ReturnResult<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
    Pair<BizResultVo, LoginResponse> result = loginService.loginWithPassword(request);
    if (result.getLeft().isSuccess()) {
      return ReturnResult.success(result.getRight());
    }
    return ReturnResult.error(result.getKey().getCode());
  }

  public void loginWithAuthCode() {

  }

  public void loginOut() {

  }

}
