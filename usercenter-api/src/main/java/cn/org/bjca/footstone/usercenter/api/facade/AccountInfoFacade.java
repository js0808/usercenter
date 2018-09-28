package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountChangeRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountRegisterRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountStatusUpdateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ModifyPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.RegisterCertRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ResetPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountRegisterResponse;
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
  ReturnResult<AccountRegisterResponse> register(AccountRegisterRequest request);

  @ApiOperation(value = "密码重置", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "密码重置")
  @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult resetPassword(ResetPasswordRequest request);

  @ApiOperation(value = "更新账号状态", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "更新账号状态")
  @RequestMapping(value = "/accountStatus", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult accountStatus(AccountStatusUpdateRequest request);

  @ApiOperation(value = "修改密码", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "修改密码")
  @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult modifyPassword(ModifyPasswordRequest request);

  @ApiOperation(value = "帐号变更", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "帐号变更")
  @RequestMapping(value = "/accountChange", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult accountChange(AccountChangeRequest request);

  @ApiOperation(value = "使用证书注册", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "使用证书注册")
  @RequestMapping(value = "/register/cert", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult registerByCert(RegisterCertRequest request);

  @ApiOperation(value = "账号是否存才", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "账号是否存在")
  @RequestMapping(value = "/checkAccount", method = RequestMethod.GET)
  @ResponseBody
  ReturnResult checkAccount(AccountInfoRequest accountInfoRequest);
}
