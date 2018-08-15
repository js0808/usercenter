package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoCheakRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:企业信息
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/

@Api(value = "企业信息管理接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "企业信息管理接口")
@RequestMapping("/entinfos/")
public interface EntInfoFacade {

  public ReturnResult addEntInfo(EntInfoRequest entInfoRequest);

  @ApiOperation(value = "添加企业信息，并实名认证", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "添加企业信息，并实名认证")
  @ApiImplicitParams(value = {
      @ApiImplicitParam(name = "entInfoCheakRequest", value = "EntInfoCheakRequest payload", required = true, paramType = "body", dataType = "EntInfoCheakRequest")
  })
  @RequestMapping(value = "/checkreal", method = RequestMethod.POST)
  public ReturnResult checkRealEntInfo(EntInfoCheakRequest entInfoCheakRequest);

}
