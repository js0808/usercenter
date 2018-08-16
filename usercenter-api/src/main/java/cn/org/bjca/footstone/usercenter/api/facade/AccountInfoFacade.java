package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountRegisterRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ResetPasswordRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Api(value = "帐号相关接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "帐号相关接口")
@RequestMapping("/account")
public interface AccountInfoFacade {

  @ApiOperation(value = "帐号注册", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "帐号注册")
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult register(AccountRegisterRequest request);

  @ApiOperation(value = "密码重置", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "密码重置")
  @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult resetPassword(ResetPasswordRequest request);

}
