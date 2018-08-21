package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.valid.AccountStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AccountStatusUpdateRequest {

  @NotBlank(message = "请输入正确的注册帐号")
  private String account;

  @AccountStatus
  private String status;
}