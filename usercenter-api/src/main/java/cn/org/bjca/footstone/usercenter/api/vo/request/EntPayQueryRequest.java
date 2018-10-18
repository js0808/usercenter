package cn.org.bjca.footstone.usercenter.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:发起企业打款请求
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Data
public class EntPayQueryRequest {

  @ApiModelProperty(value = "appId")
  @Length(max = 64)
  private String appId = null;

  @ApiModelProperty(value = "查询ID", required = true)
  @NotBlank
  private String queryTransId = null;

  @ApiModelProperty(value = "验证码", required = true)
  @NotBlank
  private String verifyCode = null;

  @ApiModelProperty(value = "用户UID", required = true)
  @NotNull
  private Long uid;

}
