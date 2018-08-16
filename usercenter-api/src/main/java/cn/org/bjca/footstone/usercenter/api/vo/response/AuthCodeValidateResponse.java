package cn.org.bjca.footstone.usercenter.api.vo.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("验证码验证返回参数")
public class AuthCodeValidateResponse {

  private String id;
}