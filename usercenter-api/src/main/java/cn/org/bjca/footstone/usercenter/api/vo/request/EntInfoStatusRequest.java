package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description:企业状态修改
 * @author: ZHAOZHIWEI
 * @create: 2018/8/16
 **/
@Data
public class EntInfoStatusRequest {

  @NotNull(message = "不能为空并且枚举值只能为 NORMAL, FREEZE,  INVALID")
  private UserInfoStatusEnum status;
  private String oper;
}
