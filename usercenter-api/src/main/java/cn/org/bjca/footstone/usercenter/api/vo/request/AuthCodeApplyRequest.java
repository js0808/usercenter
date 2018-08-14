package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.valid.AuthCodeType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@ApiModel("短信验证码申请请求参数")
public class AuthCodeApplyRequest {
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "请输入正确的手机号码")
    private String mobile;
    @AuthCodeType
    private String type;
}