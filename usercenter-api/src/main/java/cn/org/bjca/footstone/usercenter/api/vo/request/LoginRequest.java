package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.valid.LoginChannel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author LvYong
 * @create 2018/8/16
 **/
@Data
public class LoginRequest {

  @ApiModelProperty(value = "appId")
  @NotBlank(message = "应用ID不能为空")
  @Length(max = 64)
  private String appId = null;

  @NotBlank(message = "用户名不能为空")
  @Length(max = 128)
  private String username;

  private String password;

  private String authCode;

  private int expireMinutes;

  private String clientInfo;

  @LoginChannel
  private String channel;
}