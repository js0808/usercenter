package cn.org.bjca.footstone.usercenter.api.vo.request;

import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/27
 * @since 1.0
 */
@Data
public class CaptchaValidateVo {

  @NotBlank(message = "ticket 不能为空")
  @Size(min = 32, max = 32, message = "ticket 长度为32位")
  private String ticket;
  @NotBlank
  @Size(min = 6, max = 8, message = "验证码长度为6-8位")
  private String code;

}
