package cn.org.bjca.footstone.usercenter.api.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author LvYong
 * @create 2018/8/16
 **/
@Data
public class LoginCertRequest {

  @ApiModelProperty(value = "appId")
  @NotBlank(message = "应用ID不能为空")
  @Length(max = 64)
  private String appId = null;

  @NotBlank(message = "用户证书")
  private String userCert;

  //验证证书服务使用certPolicyId验证证书，需要业务服务和证书服务提前确认这个值
  private String certPolicyId;

  @NotBlank(message = "登录签名")
  private String sign;

  @NotBlank(message = "签名算法标识符")
  private String signAlgIdentifier;

  @NotBlank(message = "签名原文")
  private String source;

  private int expireMinutes;

  private String clientInfo;

  @NotBlank(message = "登录渠道不能为空")
  private String channel;
}