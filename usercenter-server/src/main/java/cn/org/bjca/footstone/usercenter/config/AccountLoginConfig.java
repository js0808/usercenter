package cn.org.bjca.footstone.usercenter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "bjca.account.login")
public class AccountLoginConfig {

  private int maxAttempts = 5;

  private int attemptsDurationMinutes = 5;

  private int lockedExpireMinutes = 30;

  private int tokenExpireMinutes = 30;

}
