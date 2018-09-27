package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoBaseRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoStatusRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:企业信息
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/

@Api(value = "企业信息管理接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "企业信息管理接口")
@RequestMapping("/entinfos")
public interface EntInfoFacade {

  @ApiOperation(value = "用UID查询企业信息", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "用UID查询企业信息")
  @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
  public ReturnResult getEntInfoByUid(@PathVariable Long uid);


  @ApiOperation(value = "修改企业信息(实名认证)", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT", notes = "修改企业信息(实名认证)")
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "entInfoRequest", value = "EntInfoRequest payload", required = true, paramType = "body", dataType = "EntInfoRequest")
  })
  @RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
  public ReturnResult updateEntInfo(@PathVariable Long uid,
      @RequestBody @Validated EntInfoRequest entInfoRequest);

  @ApiOperation(value = "添加企业信息(实名认证)", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "添加企业信息(实名认证)")
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "entInfoRequest", value = "EntInfoRequest payload", required = true, paramType = "body", dataType = "EntInfoRequest")
  })

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ReturnResult<EntInfoResponse> addEntInfo(
      @RequestBody @Validated EntInfoRequest entInfoRequest);

  @ApiOperation(value = "修改企业状态", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT", notes = "修改企业状态")
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "entInfoStatusRequest", value = "EntInfoStatusRequest payload", required = true, paramType = "body", dataType = "EntInfoStatusRequest")
  })

  @RequestMapping(value = "/{uid}/status", method = RequestMethod.PUT)
  public ReturnResult updateEntStatus(@PathVariable Long uid,
      @RequestBody @Validated EntInfoStatusRequest entInfoStatusRequest);

  @ApiOperation(value = "通过企业账号查询企业信息", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "通过企业账号查询企业信息")
  @RequestMapping(value = "", method = RequestMethod.GET)
  ReturnResult query(EntInfoQueryRequest request);

  @ApiOperation(value = "修改无需实名认证的企业信息", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT", notes = "修改无需实名认证的企业信息")
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "entInfoBaseRequest", value = "EntInfoBaseRequest payload", required = true, paramType = "body", dataType = "EntInfoBaseRequest")
  })
  @RequestMapping(value = "/{uid}/simple", method = RequestMethod.PUT)
  public ReturnResult updateEntInfoSimple(@PathVariable Long uid,
      @RequestBody @Validated EntInfoBaseRequest entInfoBaseRequest);

  @ApiOperation(value = "发起企业打款，打款附言为验证码", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT", notes = "发起企业打款，打款附言为验证码")
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "entPayVerifyRequest", value = "EntPayRequest payload", required = true, paramType = "body", dataType = "EntPayRequest")
  })
  @RequestMapping(value = "/payVerify", method = RequestMethod.POST)
  public ReturnResult entPayVerify(@RequestBody @Validated EntPayRequest payVerifyRequest);

  @ApiOperation(value = "使用附言验证码验证企业打款", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT", notes = "使用附言验证码验证企业打款")
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "entPayVerifyRequest", value = "EntPayRequest payload", required = true, paramType = "body", dataType = "EntPayRequest")
  })
  @RequestMapping(value = "/payVerify", method = RequestMethod.GET)
  public ReturnResult entPayVerifyQuery(@RequestBody @Validated EntPayQueryRequest payQueryRequest);

}
