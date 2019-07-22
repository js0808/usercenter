package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EnterpriseSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountRegisterResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Api(value = "帐号同步相关接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "帐号同步相关接口")
@RequestMapping("/accountinfo")
public interface AccountSyncFacade {

  @ApiOperation(value = "企业信息同步", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "企业信息同步")
  @RequestMapping(value = "/ent/sync", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult<AccountRegisterResponse> syncEnterprise(EnterpriseSyncRequest request);

  @ApiOperation(value = "账号信息同步", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "账号信息同步")
  @RequestMapping(value = "/sync", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult syncAccount(AccountSyncRequest request);
}
