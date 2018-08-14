package cn.org.bjca.footstone.usercenter.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/4/13
 * @since 1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

  private static ApplicationContext ctx = null;

  @Override
  public void setApplicationContext(
      ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }

  public static ApplicationContext get() {
    return ctx;
  }

  public static String getProperties(String key) {
    return env().getProperty(key);
  }

  public static Environment env() {
    return ctx.getEnvironment();
  }
}
