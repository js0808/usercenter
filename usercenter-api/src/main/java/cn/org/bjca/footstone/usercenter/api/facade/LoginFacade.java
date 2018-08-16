package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.LoginResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Api(value = "用户登录相关接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "用户登录相关接口")
public interface LoginFacade {

  @ApiOperation(value = "登录", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "用户登录")
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult<LoginResponse> login(@Validated @RequestBody LoginRequest request);

}
