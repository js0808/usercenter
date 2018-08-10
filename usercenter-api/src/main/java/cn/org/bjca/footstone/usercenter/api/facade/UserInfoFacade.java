package cn.org.bjca.footstone.usercenter.api.facade;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author LvYong
 * @create 2017-12-13
 **/
@Api(value = "用户相关接口", consumes = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP", description = "用户相关接口")
@RequestMapping("/demo/xxxx/v1/users")
public interface UserInfoFacade {

    @ApiOperation(value = "查询用户信息", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", notes = "查询用户信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path", dataType = "int")
    })
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    ReturnResult<UserResponse> findById(@PathVariable("userId") Integer userId);
}
