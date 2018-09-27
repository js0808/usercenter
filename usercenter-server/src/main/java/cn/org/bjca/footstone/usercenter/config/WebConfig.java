package cn.org.bjca.footstone.usercenter.config;

import cn.org.bjca.footstone.usercenter.web.interceptors.TraceIdInterceptor;
import com.google.common.base.Strings;
import java.io.File;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.ApplicationHome;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author LvYong
 * @create 2018-03-12
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(new TraceIdInterceptor());
  }

  @Bean
  public MultipartConfigElement multipartConfigElement(MultipartProperties multipartProperties) {
    String location = multipartProperties.getLocation();
    if (Strings.isNullOrEmpty(location)) {
      ApplicationHome home = new ApplicationHome(WebConfig.class);
      File file = new File(home.getDir(), "/tmp-upload");
      if (!file.exists()) {
        file.mkdirs();
      }
      multipartProperties.setLocation(file.getAbsolutePath());
    }
    return multipartProperties.createMultipartConfig();
  }
}
