package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.CaptchaValidateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/27
 * @since 1.0
 */
@Api(value = "图片验证码接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "图片验证码接口")
@RequestMapping("/captcha")
public interface CaptchaFacade {

  @ApiOperation(value = "获取验证码", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "获取验证码")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  ReturnResult get(String appId);


  @ApiOperation(value = "验证验证码", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "验证验证码")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult validate(@RequestBody CaptchaValidateVo vo);
}
