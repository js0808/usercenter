package cn.org.bjca.footstone.usercenter.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @description:企业基本信息不用实名认证的属性
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Data
public class EntInfoBaseRequest {

  @ApiModelProperty(value = "appId")
  @Length(max = 64)
  private String appId = null;

  @ApiModelProperty(value = "企业头像地址")
  @Length(max = 256)
  private String headImgUrl = null;

  @ApiModelProperty(value = "企业电话")
  @Length(max = 64)
  private String phone = null;

  @ApiModelProperty(value = "操作者账号")
  @Length(max = 128)
  private String oper = null;

  @ApiModelProperty(value = "法人身份证正面地址")
  @Length(max = 256)
  private String legalIdFrontImageUrl = null;

  @ApiModelProperty(value = "法人身份证反面地址")
  @Length(max = 256)
  private String legalIdBackImageUrl = null;

}
