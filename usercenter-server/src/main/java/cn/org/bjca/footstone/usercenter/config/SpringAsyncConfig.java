package cn.org.bjca.footstone.usercenter.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "spring.threadpool")
//@EnableAsync
public class SpringAsyncConfig {

  private int corePoolSize;
  private int maxPoolSize;
  private int queueCapacity;
  private int keepAliveSeconds;

  @Bean(name = "threadPoolTaskExecutor")
  public Executor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setKeepAliveSeconds(keepAliveSeconds);
    executor.setThreadNamePrefix("threadPoolExecutor-");
    executor.initialize();
    return executor;
  }

}
