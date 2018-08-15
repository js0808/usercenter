package cn.org.bjca.footstone.usercenter.api.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@ApiModel("验证码验证请求参数")
public class AuthCodeValidateRequest {
    @NotBlank(message = "应用标识不能为空")
    private String appId;
    @NotBlank(message = "手机号码不能为空")
    private String mobile;
    @NotBlank(message = "transId不能为空")
    private String transId;
    @NotBlank(message = "templateId不能为空")
    private String templateId;
    @NotBlank(message = "验证码不能为空")
    private String authCode;
}