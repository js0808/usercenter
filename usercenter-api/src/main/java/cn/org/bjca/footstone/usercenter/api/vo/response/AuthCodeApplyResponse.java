package cn.org.bjca.footstone.usercenter.api.vo.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("验证码申请返回参数")
public class AuthCodeApplyResponse {
    private String id;
    private Integer serialNo;
}