package cn.org.bjca.footstone.usercenter.biz.realname;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import cn.org.bjca.footstone.usercenter.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
public class RealNameCheckerTest extends BaseTest {

  @Autowired
  private RealNameChecker checker;

  @Test
  public void testGetVerify() {
    RealNameVerify verify1 = checker.getVerify("name_and_id_num");
    RealNameVerify verify2 = checker.getVerify("name_and_id_num");
    assertNotNull(verify1);
    assertNotEquals(verify1, verify2);
  }
}