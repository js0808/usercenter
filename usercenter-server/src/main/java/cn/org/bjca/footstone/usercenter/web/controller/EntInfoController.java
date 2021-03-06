package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.EntInfoFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoBaseRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoStatusRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryEntInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.EntInfoService;
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
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "用UID查询企业信息");
    try {
      QueryEntInfoResponse entInfo = entInfoService.getEntInfo(uid);
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



  @Override
  public ReturnResult query(EntInfoQueryRequest request) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "通过Account查询企业信息");
    try {
      ReturnResult result = ReturnResult.success(entInfoService.queryEntInfo(request));
      metricsClient.sr_incrSuccess();
      return result;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  @Override
  public ReturnResult entPay(@RequestBody @Validated EntPayRequest payRequest) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "发起企业打款");
    try {
      ReturnResult result = ReturnResult.success(entInfoService.entPay(payRequest));
      metricsClient.sr_incrSuccess();
      return result;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  @Override
  public ReturnResult entPayVerify(
      @RequestBody @Validated EntPayQueryRequest payQueryRequest) {
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "查询验证企业打款");
    try {
      entInfoService.entPayQuery(payQueryRequest);
      metricsClient.sr_incrSuccess();
      return ReturnResult.success("成功");
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }
}
