package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.UserInfoFacade;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserResponse;
import cn.org.bjca.footstone.usercenter.biz.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LvYong
 * @create 2018-03-06
 **/
@Slf4j
@RestController
public class UserController implements UserInfoFacade {

    @Autowired
    private UserInfoService userService;

    @Override
    public ReturnResult<UserResponse> findById(@PathVariable Integer userId) {
        log.info("userId:{}", userId);
        UserResponse userResponse = userService.findById(userId);
        return ReturnResult.success(userResponse);
    }
}
