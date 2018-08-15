package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoSimpleVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryUserInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Api(value = "用户相关接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "用户相关接口")
@RequestMapping("/users")
public interface UserInfoFacade {

  @ApiOperation(value = "新增用户(实名认证)", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", notes = "新增用户(实名认证)")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  ReturnResult<UserInfoResponse> addUser(UserInfoVo userInfo);

  @ApiOperation(value = "变更用户信息(实名认证)", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT", notes = "变更用户信息(实名认证)")
  @RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
  @ResponseBody
  ReturnResult<UserInfoResponse> modUser(@PathVariable Integer uid,
      @RequestBody UserInfoVo userInfo);

  @ApiOperation(value = "查询用户信息", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "查询用户信息")
  @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
  @ResponseBody
  ReturnResult<QueryUserInfoResponse> getUser(@PathVariable Integer uid);

  @ApiOperation(value = "变更用户信息(基本信息)", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "PUT", notes = "变更用户信息(基本信息)")
  @RequestMapping(value = "/{uid}/simple", method = RequestMethod.PUT)
  @ResponseBody
  ReturnResult<UserInfoResponse> modUserSimple(@PathVariable Integer uid,
      @RequestBody UserInfoSimpleVo userInfoSimpleVo);
}
