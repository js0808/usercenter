package cn.org.bjca.footstone.usercenter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:baoqb
 * @Date:18/8/14
 */
@Data
@Component
@ConfigurationProperties(prefix = "authcode")
public class AuthCodeConfig {
    private String appId;
    private String templateId;
    private String signAlgo;
    private String signKey;
    private String codeUrl;
    private String emailUrl;
    private String validateUrl;
    private String version;
    private Integer expire;
    private String fromName;
}