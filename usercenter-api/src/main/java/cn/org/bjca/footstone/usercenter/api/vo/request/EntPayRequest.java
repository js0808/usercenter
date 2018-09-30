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
public class EntPayRequest extends EntInfoRequest {

  @ApiModelProperty(value = "账号", required = true)
  @NotBlank
  @Length(max = 64)
  private String bankAccount = null;

  @ApiModelProperty(value = "开户行", required = true)
  @NotBlank
  @Length(max = 128)
  private String bankName = null;

  @ApiModelProperty(value = "开户行编码", required = true)
  @NotBlank
  @Length(max = 64)
  private String bankAddressCode = null;

  @ApiModelProperty(value = "用户UID", required = true)
  @NotNull
  private Long uid;

}
