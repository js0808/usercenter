package cn.org.bjca.footstone.usercenter.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @description:ent info
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Data
public class EntInfoRequest {

  @ApiModelProperty(value = "企业头像地址")
  @Length(max = 256)
  private String headImgUrl = null;

  @ApiModelProperty(value = "企业电话")
  @Length(max = 64)
  private String phone = null;

  @ApiModelProperty(value = "法人姓名")
  @Length(max = 256)
  private String legalName = null;

  @ApiModelProperty(value = "法人身份证号码")
  @Length(max = 32)
  private String legalIdNum = null;
}
