package cn.org.bjca.footstone.usercenter.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author LvYong
 * @create 2018/8/16
 **/
@Configurable
public class SpringConfig {

  @Bean
  public BCryptPasswordEncoder getBCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }
}