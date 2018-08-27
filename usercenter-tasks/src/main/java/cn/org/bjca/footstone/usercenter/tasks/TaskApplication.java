package cn.org.bjca.footstone.usercenter.tasks;

import cn.org.bjca.footstone.metrics.client.metrics.loggers.MetricsLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class TaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskApplication.class, args);
    MetricsLoggerFactory.init();
  }
}
