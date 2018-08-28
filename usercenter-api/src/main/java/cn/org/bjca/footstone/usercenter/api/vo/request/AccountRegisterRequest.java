package cn.org.bjca.footstone.usercenter.api.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AccountRegisterRequest extends BaseRequest {

  @NotBlank(message = "请输入正确的注册帐号")
  private String account;

  @NotBlank(message = "请填写密码")
  @Length(min = 6, max = 12)
  private String password;

  @NotBlank(message = "验证码不能为空")
  private String authCode;
}