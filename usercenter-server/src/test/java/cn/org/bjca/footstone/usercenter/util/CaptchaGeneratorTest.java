package cn.org.bjca.footstone.usercenter.util;

import static org.junit.Assert.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/27
 * @since 1.0
 */
@Slf4j
public class CaptchaGeneratorTest {

  @Test
  public void generate() {
    Pair<String, String> generate = CaptchaGenerator.generate();
    assertNotNull(generate);
    log.info(generate.getKey());
    log.info(generate.getValue());
  }
}