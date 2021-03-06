package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.enmus.LoginChannelEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.LoginResponse;
import cn.org.bjca.footstone.usercenter.config.AccountLoginConfig;
import cn.org.bjca.footstone.usercenter.vo.BizResultVo;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Slf4j
public class LoginServiceTest extends BaseTest {

  @Autowired
  LoginService loginService;

  @Autowired
  AccountLoginConfig accountLoginConfig;

  @Test
  public void loginWithPassword() throws Exception {
    for (int i = 0; i < 6; i++) {
      LoginRequest request = new LoginRequest();
      request.setPassword("123456");
      request.setUsername("18601030948");
      request.setExpireMinutes(60);
      request.setChannel(LoginChannelEnum.WEB.value());
      Pair<BizResultVo, LoginResponse> responsePair = loginService.loginWithPassword(request);
      System.out.println(responsePair);
    }
  }

  public LoginRequest buildSuccessRequest() {
    LoginRequest request = new LoginRequest();
    request.setPassword("123456");
    request.setUsername("18601030948");
    request.setExpireMinutes(60);
    request.setChannel(LoginChannelEnum.WEB.value());
    return request;
  }

  @Test
  public void testLoginSuccess() throws Exception {
    LoginRequest loginRequest = buildSuccessRequest();
    Pair<BizResultVo, LoginResponse> responsePair = loginService.loginWithPassword(loginRequest);
    System.out.println(responsePair);
    Assert.assertTrue(responsePair.getLeft().isSuccess());
    Assert.assertNotNull(responsePair.getRight());
  }

  @Test
  public void testLockExpire() throws InterruptedException {
    accountLoginConfig.setLockedExpireMinutes(1);
    accountLoginConfig.setMaxAttempts(5);
    for (int i = 0; i < 5; i++) {
      LoginRequest request = new LoginRequest();
      request.setPassword("1234565");
      request.setUsername("18601030948");
      request.setExpireMinutes(60);
      request.setChannel(LoginChannelEnum.WEB.value());
      try {
        Pair<BizResultVo, LoginResponse> responsePair = loginService.loginWithPassword(request);
        System.out.println(responsePair);
      } catch (Exception e) {
        log.error("{}", request, e);
      }
    }
    TimeUnit.SECONDS.sleep(90);
    logSuccess();
  }

  public void logSuccess() {
    LoginRequest request = new LoginRequest();
    request.setPassword("123456");
    request.setUsername("18601030948");
    request.setExpireMinutes(60);
    request.setChannel(LoginChannelEnum.WEB.value());
    Pair<BizResultVo, LoginResponse> responsePair = loginService.loginWithPassword(request);
    System.out.println(responsePair);
    Assert.assertTrue(responsePair.getLeft().isSuccess());
    Assert.assertNotNull(responsePair.getRight());
  }

}