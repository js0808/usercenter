package cn.org.bjca.footstone.usercenter.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class BaseRequest {

  @ApiModelProperty(value = "appId")
  @NotBlank
  @Length(max = 64)
  private String appId;

  @ApiModelProperty(value = "subAppId")
  @Length(max = 64)
  private String subAppId;
}