package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.valid.AuthCodeType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AccountChangeRequest extends BaseRequest{

  @NotBlank(message = "用户标识不能为空")
  @Length(max = 128)
  private String account;
  @NotBlank(message = "原用户标识不能为空")
  @Length(max = 128)
  private String oldAccount;
  @NotBlank(message = "验证码不能为空")
  private String authCode;
  @AuthCodeType
  private String type;
  @NotBlank(message = "验证标识信息不能为空")
  private String validateId;
}