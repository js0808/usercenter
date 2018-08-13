package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.UserInfoFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LvYong
 * @create 2018-03-06
 **/
@Slf4j
@RestController
public class UserController implements UserInfoFacade {

  @Autowired
  private UserInfoService userInfoService;

  @Override
  public ReturnResult<UserInfoResponse> addUser(@Validated UserInfoVo userInfo) {

    return null;
  }
}
