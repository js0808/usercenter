package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.valid.AuthCodeType;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class EmailCodeApplyRequest {

  @Email(message = "请输入正确的邮箱地址")
  private String email;
  @AuthCodeType
  private String type;
  @NotBlank(message = "标题不能为空")
  private String subject;
}