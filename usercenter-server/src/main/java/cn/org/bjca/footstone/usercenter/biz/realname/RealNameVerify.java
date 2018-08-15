package cn.org.bjca.footstone.usercenter.biz.realname;

import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
public abstract class RealNameVerify {

  private UserInfoVo userInfoVo;

  public void setUserInfoVo(UserInfoVo userInfoVo) {
    this.userInfoVo = userInfoVo;
  }

  public UserInfoVo getUserInfoVo() {
    return userInfoVo;
  }

  public abstract Pair<Boolean, String> checkRequest();


}
