package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum;
import lombok.Data;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/15
 * @since 1.0
 */
@Data
public class UserInfoStatusVo {

  private UserInfoStatusEnum status;
  private String oper;
}
