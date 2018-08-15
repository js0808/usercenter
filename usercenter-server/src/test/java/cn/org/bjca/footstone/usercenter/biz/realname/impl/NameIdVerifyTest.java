package cn.org.bjca.footstone.usercenter.biz.realname.impl;

import static org.junit.Assert.*;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameChecker;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@Slf4j
public class NameIdVerifyTest extends BaseTest{

  @Autowired
  private RealNameChecker checker;

  @Test
  public void test(){
    RealNameVerify verify = checker.getVerify("name_and_id_num");
    UserInfoVo vo = new UserInfoVo();
    verify.setUserInfoVo(vo);

    Pair<Boolean, String> res = verify.checkRequest();
    assertFalse(res.getKey());
    log.info("{}", res);
    vo.setName("a");
    vo.setIdNum("110101199003079251");
    Pair<Boolean, String> res1 = verify.checkRequest();
    assertTrue(res1.getKey());
    log.info("{}", res1);
  }
}