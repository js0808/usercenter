package cn.org.bjca.footstone.usercenter.api.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AccountInfoRequest {

  @NotBlank(message = "请输入正确的注册帐号")
  private String account;
}