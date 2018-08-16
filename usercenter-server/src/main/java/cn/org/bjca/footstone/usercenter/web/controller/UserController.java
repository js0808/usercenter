package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
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
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "个人信息实名认证");
    ReturnResult result;
    try {
      result = ReturnResult.success(userInfoService.addUser(userInfo));
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("个人信息实名认证异常", e);
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return result;
  }

  @Override
  public ReturnResult<UserInfoResponse> modUser(@PathVariable Long uid,
      @RequestBody UserInfoVo userInfo) {
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "个人信息变更实名认证");
    ReturnResult result;
    try {
      result = ReturnResult.success(userInfoService.modUser(uid, userInfo));
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("个人信息变更实名认证异常", e);
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return result;
  }

  @Override
  public ReturnResult<QueryUserInfoResponse> getUser(@PathVariable Long uid) {
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "个人信息查询");
    ReturnResult result;
    try {
      result = ReturnResult.success(userInfoService.getUser(uid));
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("个人信息查询异常", e);
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return result;
  }

  @Override
  public ReturnResult<UserInfoResponse> modUserSimple(@PathVariable Long uid,
      @RequestBody UserInfoSimpleVo userInfoSimpleVo) {
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "个人基本信息修改");
    ReturnResult result;
    try {
      result = ReturnResult
          .success(userInfoService.modUserSimple(uid, userInfoSimpleVo));
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("个人基本信息修改异常", e);
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return result;
  }

  @Override
  public ReturnResult<UserInfoResponse> modUserStatus(@PathVariable Long uid,
      @RequestBody UserInfoStatusVo userInfoStatusVo) {
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "个人状态变更");
    ReturnResult result;
    try {
      result = ReturnResult
          .success(userInfoService.modUserStatus(uid, userInfoStatusVo));
      metricsClient.sr_incrSuccess();
    } catch (Exception e) {
      log.error("个人状态变更异常", e);
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return result;
  }


}
