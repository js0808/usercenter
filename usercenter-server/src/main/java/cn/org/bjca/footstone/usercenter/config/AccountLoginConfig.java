package cn.org.bjca.footstone.usercenter.config;

import cn.bjca.typhon.client.api.TyphonClient;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "account.login")
public class AccountLoginConfig {

  private int maxAttempts = 5;

  private int attemptsDurationMinutes = 5;

  private int lockedExpireMinutes = 30;

  private int tokenExpireMinutes = 30;

  @PostConstruct
  public void init() {
    TyphonClient.getInstance().getProperties("application").registerListener(
        propertyChangeEvent -> {
          log.info("account.login. 发生变化 {}", propertyChangeEvent.getChangeItems());
        }, "account.login.max-attempts", "account.login.attempts-duration-minutes",
        "account.login.locked-expire-minutes", "account.login.token-expire-minutes");
  }

}
