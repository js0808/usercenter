package cn.org.bjca.footstone.usercenter.tasks.lock;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AvailableCheckingJob {

  private static final String KEY = "usercenter.tasks.lock";
  private static final int EXPIRE_SECONDS = 90;
  private static String identity;

  static {
    //默认值标识
    identity = UUID.randomUUID().toString();
    try {
      identity = InetAddress.getLocalHost().getHostAddress();
      log.info("获取本机ip为：{}", identity);
    } catch (UnknownHostException e) {
      LoggerFactory.getLogger(AvailableCheckingJob.class).error("获取本机ip异常", e);
    }
  }

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Scheduled(fixedDelay = 60000)
  public void checking() {
    log.info("server AvailableCheckingJob start,ip:{}", identity);
    long expireTo = System.currentTimeMillis() + (EXPIRE_SECONDS * 1000);
    String value = identity + "#" + expireTo;
    Boolean setSuccess = stringRedisTemplate.opsForValue().setIfAbsent(KEY, value);

    if (setSuccess) {
      //抢到锁
      JobRunningStatus.setSuccess(true);
      log.info("setNx抢到锁，并设置缓存值：{}", value);
    } else {
      //没有抢到锁
      String lockValue = null;
      try {
        lockValue = stringRedisTemplate.opsForValue().get(KEY);
      } catch (Exception e) {
        log.error("redis.get操作异常，KEY:{}", KEY, e);
      }
      if (StringUtils.isNotBlank(lockValue)) {
        String[] arr = StringUtils.split(lockValue, "#");
        if (StringUtils.equals(identity, arr[0])) {
          //是自己锁定的 更新value值
          try {
            stringRedisTemplate.opsForValue().set(KEY, value);
          } catch (Exception e) {
            log.error("更新锁的value值redis.set操作异常，KEY:{},value:{}", KEY, value, e);
          }
          JobRunningStatus.setSuccess(true);
          log.info("get到自己锁定的锁，并更新缓存值：{}", value);
        } else {
          long exp = NumberUtils.toLong(arr[1], 0);
          if (exp != 0 && exp < System.currentTimeMillis()) {
            //其他机器锁定值过期了
            String old = stringRedisTemplate.opsForValue().getAndSet(KEY, value);
            log.info("get到锁被其他线程锁定并已过期，执行解锁操作 更新为自己的value值：{}", value);
            if (StringUtils.equals(old, lockValue)) {
              //锁定成功
              JobRunningStatus.setSuccess(true);
            } else {
              //被别人占了 还原被锁定值
              try {
                stringRedisTemplate.opsForValue().set(KEY, old);
              } catch (Exception e) {
                log.error("还原锁的value值redis.set操作异常，KEY:{},value:{}", KEY, old, e);
              }
              JobRunningStatus.setSuccess(false);
              log.info("锁定失败，该锁已被其他进程抢先锁定，更新值为getset之前的值：{}", old);
            }
          } else {
            JobRunningStatus.setSuccess(false);
          }
        }
      } else {
        JobRunningStatus.setSuccess(false);
      }
    }
  }
}