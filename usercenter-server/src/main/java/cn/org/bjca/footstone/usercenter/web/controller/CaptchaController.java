package cn.org.bjca.footstone.usercenter.web.controller;

import static cn.org.bjca.footstone.usercenter.Conts.CAPTCHA_EXPIRE_MIN;
import static cn.org.bjca.footstone.usercenter.Conts.CAPTCHA_REDIS_PREFIX;
import static java.util.Objects.isNull;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.facade.CaptchaFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.CaptchaValidateVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.CaptchaVo;
import cn.org.bjca.footstone.usercenter.util.CaptchaGenerator;
import com.google.common.base.Strings;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/27
 * @since 1.0
 */
@Slf4j
@RestController
public class CaptchaController implements CaptchaFacade {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Override
  public ReturnResult get(String appId) {
    log.info("请求appId {}", appId);
    Pair<String, String> generate = CaptchaGenerator.generate();
    if (isNull(generate)) {
      log.error("生成验证码异常!");
      return ReturnResult.error(ReturnCodeEnum.PATCHCA_GENERATE_ERROR);
    }
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    redisTemplate.opsForValue()
        .set(createKey(uuid), generate.getKey(), CAPTCHA_EXPIRE_MIN, TimeUnit.MINUTES);
    return ReturnResult.success(new CaptchaVo(uuid, generate.getValue()));
  }

  private String createKey(String uuid) {
    return CAPTCHA_REDIS_PREFIX + uuid;
  }

  @Override
  public ReturnResult validate(@Validated @RequestBody CaptchaValidateVo vo) {
    log.info("请求验证 {}", vo);
    String key = createKey(vo.getTicket());
    String random = redisTemplate.opsForValue().get(key);
    if (Strings.isNullOrEmpty(random)) {
      return ReturnResult.error(ReturnCodeEnum.PATCHCA_EXPIRE_ERROR);
    }
    try {
      if (Objects.equals(random, vo.getCode())) {
        return ReturnResult.success("验证成功");
      }
      return ReturnResult.error(ReturnCodeEnum.PATCHCA_VALIDATE_ERROR);
    } finally {
      redisTemplate.delete(key);
    }
  }
}
