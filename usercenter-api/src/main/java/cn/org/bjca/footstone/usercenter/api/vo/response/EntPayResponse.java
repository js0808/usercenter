package cn.org.bjca.footstone.usercenter.api.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @description:企业打款响应，返回查询ID，后续用ID和附言中验证码来验证
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Data
@Builder
public class EntPayResponse {

  @ApiModelProperty(value = "企业打款认证查询ID")
  private String queryTransId = null;
}
