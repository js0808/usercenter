package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.UserInfoFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoSimpleVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoStatusVo;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryUserInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.UserInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@SuppressWarnings("unchecked")
@Slf4j
@RestController
public class UserController implements UserInfoFacade {

  @Autowired
  private UserInfoService userInfoService;


  @Override
  public ReturnResult<UserInfoResponse> addUser(@RequestBody UserInfoVo userInfo) {
    return ReturnResult.success(userInfoService.addUser(userInfo));
  }

  @Override
  public ReturnResult<UserInfoResponse> modUser(@PathVariable Integer uid,
      @RequestBody UserInfoVo userInfo) {
    return ReturnResult.success(userInfoService.modUser(uid, userInfo));
  }

  @Override
  public ReturnResult<QueryUserInfoResponse> getUser(@PathVariable Integer uid) {
    return ReturnResult.success(userInfoService.getUser(uid));
  }

  @Override
  public ReturnResult<UserInfoResponse> modUserSimple(@PathVariable Integer uid,
      @RequestBody UserInfoSimpleVo userInfoSimpleVo) {
    return ReturnResult.success(userInfoService.modUserSimple(uid, userInfoSimpleVo));
  }

  @Override
  public ReturnResult<UserInfoResponse> modUserStatus(@PathVariable Integer uid,
      @RequestBody UserInfoStatusVo userInfoStatusVo) {
    return ReturnResult.success(userInfoService.modUserStatus(uid, userInfoStatusVo));
  }


}
