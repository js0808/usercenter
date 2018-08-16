package cn.org.bjca.footstone.usercenter.biz.realname.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.UserInfoService;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameChecker;
import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoExample;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import com.alibaba.fastjson.JSON;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@Slf4j
public class NameIdVerifyTest extends BaseTest {

  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserInfoMapper userInfoMapper;

  @Autowired
  private RealNameChecker checker;

  @Test
  public void test() {
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

  @Test
  public void testBusinessSuccess() {
    UserInfoVo vo = new UserInfoVo();
    vo.setRealNameType("name_and_id_num");
    vo.setName("a");
    vo.setIdNum("110101199003079251");
    UserInfoResponse response = userInfoService.addUser(vo);
    log.info(JSON.toJSONString(response));

    UserInfo userInfo = getUserInfoWithUid(response.getUid());
    log.info(JSON.toJSONString(userInfo));

    userInfoMapper.deleteByPrimaryKey(userInfo.getId());
  }

  @Test(expected = BaseException.class)
  public void testBusinessFail() {
    UserInfoVo vo = new UserInfoVo();
    vo.setRealNameType("name_and_id_num");
    vo.setName("a");
    vo.setIdNum("110101199003079251123");
    UserInfoResponse response = userInfoService.addUser(vo);
    log.info(JSON.toJSONString(response));
  }

  private UserInfo getUserInfoWithUid(Long uid) {
    UserInfoExample example = new UserInfoExample();
    example.createCriteria().andUidEqualTo(uid);
    List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
    return userInfos.isEmpty() ? null : userInfos.get(0);
  }

}