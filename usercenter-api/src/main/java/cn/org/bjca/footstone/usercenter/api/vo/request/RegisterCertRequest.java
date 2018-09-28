package cn.org.bjca.footstone.usercenter.api.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class RegisterCertRequest extends BaseRequest {

  @NotBlank(message = "用户证书")
  private String userCert;

  @NotBlank(message = "登录签名")
  private String sign;

  @NotBlank(message = "原文")
  private String source;

  @NotBlank(message = "签名算法标识符")
  private String signAlgIdentifier;
}