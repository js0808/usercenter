package cn.org.bjca.footstone.usercenter.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:ent info
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Data
public class EntInfoCheakRequest extends EntInfoRequest {

  @ApiModelProperty(value = "实名认证类型", required = true)
  @NotBlank
  @Length(max = 64)
  private String realNameType = null;

  @ApiModelProperty(value = "企业名称", required = true)
  @NotBlank
  @Length(max = 256)
  private String name = null;

  @ApiModelProperty(value = "组织机构代码", required = true)
  @Length(max = 256)
  private String orgCode = null;

  @ApiModelProperty(value = "营业执照号码", required = true)
  @Length(max = 256)
  private String bizLicense = null;

  @ApiModelProperty(value = "统一社会信用代码", required = true)
  @Length(max = 256)
  private String socialCreditCode = null;

}