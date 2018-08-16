package cn.org.bjca.footstone.usercenter.api.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author LvYong
 * @create 2018/8/16
 **/
@Data
public class LoginRequest {

  @NotBlank(message = "用户名不能为空")
  @Length(max = 128)
  private String username;

  private String password;

  private String authcode;

  private int expireMinutes;

  private String clientInfo;

  @NotBlank(message = "登录渠道不能为空")
  private String channel;
}
