package cn.org.bjca.footstone.usercenter.util;

import static org.junit.Assert.assertNotNull;

import cn.org.bjca.footstone.usercenter.BaseTest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/15
 * @since 1.0
 */
@Slf4j
public class RestUtilsTest extends BaseTest {

  @Test
  public void testDownload() {
    byte[] bytes = RestUtils.downLoad("https://www.baidu.com/img/bd_logo1.png?where=super");
    log.info("{}", JSON.toJSONString(bytes));
    String encodedString = Base64.encode(bytes);
    log.info(encodedString);
    assertNotNull(encodedString);
  }
}