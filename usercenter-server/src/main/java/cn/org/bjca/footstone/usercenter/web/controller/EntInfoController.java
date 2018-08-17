package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.EntInfoFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoBaseRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoStatusRequest;
import cn.org.bjca.footstone.usercenter.biz.EntInfoService;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:ent info api
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/

@RestController
public class EntInfoController implements EntInfoFacade {

  @Autowired
  EntInfoService entInfoService;

  @Override
  public ReturnResult updateEntInfo(@PathVariable Long uid,
      @RequestBody @Validated EntInfoRequest entInfoRequest) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "修改企业信息(实名认证)");
    try {
      entInfoService.updateEntInfo(uid, entInfoRequest);
      metricsClient.sr_incrSuccess();

      return ReturnResult.success("成功");
    } catch (Exception e) {
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  @Override
  public ReturnResult addEntInfo(@RequestBody @Validated EntInfoRequest entInfoRequest) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "添加企业信息(实名认证)");
    try {
      entInfoService.addEntInfo(entInfoRequest);
      metricsClient.sr_incrSuccess();

      return ReturnResult.success("成功");
    } catch (Exception e) {
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  @Override
  public ReturnResult updateEntStatus(@PathVariable Long uid,
      @RequestBody @Validated EntInfoStatusRequest entInfoStatusRequest) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "修改企业状态");
    try {
      entInfoService.updateEntStatus(uid, entInfoStatusRequest);
      metricsClient.sr_incrSuccess();

      return ReturnResult.success("成功");
    } catch (Exception e) {
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  @Override
  public ReturnResult getEntInfoByUid(@PathVariable Long uid) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "修改企业状态");
    try {
      EntInfo entInfo = entInfoService.getEntInfoByUid(uid);
      metricsClient.sr_incrSuccess();

      return ReturnResult.success(entInfo);
    } catch (Exception e) {
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  @Override
  public ReturnResult updateEntInfoSimple(@PathVariable Long uid,
      @RequestBody @Validated EntInfoBaseRequest entInfoBaseRequest) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "修改无需实名认证的企业信息");
    try {
      entInfoService.updateEntInfoSimple(uid, entInfoBaseRequest);
      metricsClient.sr_incrSuccess();

      return ReturnResult.success("成功");
    } catch (Exception e) {
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }
}
