package cn.org.bjca.footstone.usercenter.biz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoQueryVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoSimpleVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoStatusVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryUserInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoExample;
import com.alibaba.fastjson.JSON;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@Slf4j
public class UserInfoServiceTest extends BaseTest {

  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserInfoMapper userInfoMapper;

  @Test
  public void addUser() {
    UserInfoResponse response = addUserMock();
    assertNotNull(response);

  }

  private UserInfoResponse addUserMock() {
    UserInfoVo vo = new UserInfoVo();
    vo.setName("舒威");
    vo.setRealNameType("name_and_id_num");
    vo.setIdNum("110101199003079251");

    UserInfoResponse response = userInfoService.addUser(vo);
    log.info("{}", response);
    return response;
  }

  @Test
  public void modUser() {
    UserInfoResponse response = addUserMock();
    UserInfoVo vo = new UserInfoVo();
    vo.setName("舒威一");
    vo.setRealNameType("name_and_id_num");
    vo.setIdNum("110101199003079251");

    UserInfoResponse modUser = userInfoService.modUser(response.getUid(), vo);
    UserInfo userInfo = getUserInfoWithUid(modUser.getUid());
    log.info("{}", JSON.toJSONString(userInfo));
    assertNotNull(modUser);
    userInfoMapper.deleteByPrimaryKey(userInfo.getId());

  }

  private UserInfo getUserInfoWithUid(Long uid) {
    UserInfoExample example = new UserInfoExample();
    example.createCriteria().andUidEqualTo(uid);
    List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
    return userInfos.isEmpty() ? null : userInfos.get(0);
  }


  @Test
  public void getUser() {
    QueryUserInfoResponse user = userInfoService.getUser(82404904060583936L);
    log.info(JSON.toJSONString(user));
  }

  @Test
  public void modUserSimple() {
    UserInfoResponse response = addUserMock();
    UserInfoSimpleVo vo = new UserInfoSimpleVo();
    vo.setEmail("shuwei@bjca.org.cn");
    vo.setHeadImgUrl("http://www.baidu.com");

    UserInfoResponse modUser = userInfoService.modUserSimple(response.getUid(), vo);
    UserInfo userInfo = getUserInfoWithUid(modUser.getUid());
    log.info("{}", JSON.toJSONString(userInfo));
    assertNotNull(modUser);
    assertEquals(vo.getEmail(), userInfo.getEmail());
    userInfoMapper.deleteByPrimaryKey(userInfo.getId());
  }

  @Test
  public void modUserStatus() {
    UserInfoResponse response = addUserMock();
    UserInfoStatusVo statusVo = new UserInfoStatusVo();
    statusVo.setStatus(UserInfoStatusEnum.INVALID);

    UserInfoResponse modUser = userInfoService.modUserStatus(response.getUid(), statusVo);

    UserInfo userInfo = getUserInfoWithUid(modUser.getUid());
    log.info("{}", JSON.toJSONString(userInfo));
    assertNotNull(modUser);
    assertEquals(statusVo.getStatus().toString(), userInfo.getStatus());
    userInfoMapper.deleteByPrimaryKey(userInfo.getId());
  }

  @Test
  public void getUserFromAccount(){
    UserInfoQueryVo queryVo = new UserInfoQueryVo();
    queryVo.setAccount("18611525990");
    QueryUserInfoResponse userByAccount = userInfoService.getUserByAccount(queryVo);
    log.info(JSON.toJSONString(userByAccount));
    assertNotNull(userByAccount);
  }
}