package cn.org.bjca.footstone.usercenter.biz;

import static org.junit.Assert.*;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.enmus.LoginChannelEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
public class LoginServiceTest extends BaseTest {

  @Autowired
  LoginService loginService;

  @Test
  public void loginWithPassword() throws Exception {
    LoginRequest request = new LoginRequest();
    request.setPassword("123456");
    request.setUsername("18601030948");
    request.setExpireMinutes(5);
    request.setChannel(LoginChannelEnum.WEB.value());
    loginService.loginWithPassword(request);
  }

}