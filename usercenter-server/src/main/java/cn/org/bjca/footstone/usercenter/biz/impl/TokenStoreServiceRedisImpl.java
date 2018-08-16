package cn.org.bjca.footstone.usercenter.biz.impl;

import cn.org.bjca.footstone.usercenter.biz.TokenStoreService;
import cn.org.bjca.footstone.usercenter.vo.LoginTokenVo;
import com.alibaba.fastjson.JSON;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

  @Override
  public boolean removeToken(Long uid, String token) {
    String val = stringRedisTemplate.opsForValue().get(token);
    if (StringUtils.isBlank(val)) {
      return false;
    }
    LoginTokenVo tokenVo = JSON.parseObject(val, LoginTokenVo.class);
    if (tokenVo.getUid() != uid) {
      return false;
    }
    stringRedisTemplate.delete(token);
    return true;
  }

  @Override
  public LoginTokenVo readToken(Long uid, String token) {
    String val = stringRedisTemplate.opsForValue().get(token);
    if (StringUtils.isBlank(val)) {
      return null;
    }
    LoginTokenVo tokenVo = JSON.parseObject(val, LoginTokenVo.class);
    if (tokenVo.getUid() != uid) {
      return null;
    }
    return tokenVo;
  }
}
