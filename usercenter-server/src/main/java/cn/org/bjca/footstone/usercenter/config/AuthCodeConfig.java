package cn.org.bjca.footstone.usercenter.config;

import cn.bjca.typhon.client.api.TyphonClient;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:baoqb
 * @Date:18/8/14
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "authcode")
public class AuthCodeConfig {

  private String appId;
  private String deviceId;
  private String templateId;
  private String signAlgo;
  private String signKey;
  private String codeUrl;
  private String emailUrl;
  private String emailBody;
  private String validateUrl;
  private String version;
  private Integer expire;
  private String fromName;
  private String developId;
  private String developKey;


  @PostConstruct
  public void init() {
    TyphonClient.getInstance().getProperties("application").registerListener(
        propertyChangeEvent -> {
          log.info("authcode. 发生变化 {}", propertyChangeEvent.getChangeItems());
        }, "authcode.app-id", "authcode.template-id",
        "authcode.codeUrl", "authcode.sign-key",
        "authcode.emailUrl", "authcode.emailBody",
        "authcode.validateUrl", "authcode.expire",
        "authcode.deviceId", "authcode.fromName",
        "authcode.develop-id", "authcode.develop-key");
  }

}