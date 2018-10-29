package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/15
 * @since 1.0
 */
@Data
public class UserInfoStatusVo {

  @NotNull(message = "不能为空并且枚举值只能为 NORMAL, FREEZE,  INVALID")
  private UserInfoStatusEnum status;
  private String oper;
}
