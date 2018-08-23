package cn.org.bjca.footstone.usercenter.api.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ModifyPasswordRequest {

  @NotBlank(message = "请输入正确的注册帐号")
  private String account;

  @NotBlank(message = "请填写原密码")
  @Length(min = 6, max = 12)
  private String oldPassword;

  @NotBlank(message = "请填写新密码")
  @Length(min = 6, max = 12)
  private String newPassword;
}