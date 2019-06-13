package cn.org.bjca.footstone.usercenter;

import cn.bjca.typhon.client.api.spring.EnableTyphonConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableTyphonConf(filenames = "application")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
