package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.valid.AuthCodeType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AuthCodeValidateRequest {

  @NotBlank(message = "用户标识不能为空")
  private String userName;
  @NotBlank(message = "验证码不能为空")
  private String authCode;
  @AuthCodeType
  private String type;

}