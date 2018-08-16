package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.enmus.LoginChannelEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.LoginResponse;
import cn.org.bjca.footstone.usercenter.vo.BizResultVo;
import org.apache.commons.lang3.tuple.Pair;
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
    for (int i = 0; i < 6; i++) {
      LoginRequest request = new LoginRequest();
      request.setPassword("123456");
      request.setUsername("18601030948");
      request.setExpireMinutes(5);
      request.setChannel(LoginChannelEnum.WEB.value());
      Pair<BizResultVo, LoginResponse> responsePair = loginService.loginWithPassword(request);
      System.out.println(responsePair);
    }
  }

}