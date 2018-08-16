package cn.org.bjca.footstone.usercenter.biz.impl;

import cn.org.bjca.footstone.usercenter.biz.TokenStoreService;
import cn.org.bjca.footstone.usercenter.vo.LoginTokenVo;
import com.alibaba.fastjson.JSON;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Slf4j
@Service
public class TokenStoreServiceRedisImpl implements TokenStoreService {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  
  @Override
  public void store(LoginTokenVo loginTokenVo) {
    stringRedisTemplate.opsForValue().set(loginTokenVo.getToken(), JSON.toJSONString(loginTokenVo),
        loginTokenVo.getTimeoutMinutes(),
        TimeUnit.MINUTES);
  }
}
