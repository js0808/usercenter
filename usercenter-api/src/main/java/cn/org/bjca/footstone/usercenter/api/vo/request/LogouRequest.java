package cn.org.bjca.footstone.usercenter.api.vo.request;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/28
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogouRequest extends BaseRequest {
  @NotNull
  private Long uid;
  @NotBlank
  private String token;

}
