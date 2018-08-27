package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EmailCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeApplyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Api(value = "验证码相关接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "验证码相关接口")
@RequestMapping("/authcode")
public interface AuthCodeFacade {

  @ApiOperation(value = "短信验证码申请", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "短信验证码申请")
  @RequestMapping(value = "/codeApply", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult<AuthCodeApplyResponse> codeApply(AuthCodeApplyRequest request);

  @ApiOperation(value = "邮箱验证码申请", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "邮箱验证码申请")
  @RequestMapping(value = "/emailCodeApply", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult emailCodeApply(EmailCodeApplyRequest request);

  @ApiOperation(value = "证码验证", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "证码验证")
  @RequestMapping(value = "/validate", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult validate(AuthCodeValidateRequest request);

}
