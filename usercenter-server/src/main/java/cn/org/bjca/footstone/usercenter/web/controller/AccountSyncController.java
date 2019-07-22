package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.facade.AccountSyncFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EnterpriseSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountRegisterResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountSyncResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntSyncResponse;
import cn.org.bjca.footstone.usercenter.biz.AccountSyncService;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baoqingbin
 * @create 2018/8/14
 **/
@RestController
@Slf4j
public class AccountSyncController implements AccountSyncFacade {

  @Autowired
  private AccountSyncService accountSyncService;

  @Override
  public ReturnResult<AccountRegisterResponse> syncEnterprise(
      @Validated @RequestBody EnterpriseSyncRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("同步信息", "企业信息");
    EntSyncResponse response = null;
    try {
      response = accountSyncService.syncEnt(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("syncAccount 异常信息", ex);
      return ReturnResult.error(ex.getReturnStatusEnum(), ex.getMessage());
    } catch (Exception e) {
      log.error("syncAccount 异常信息", e);
      return ReturnResult.error(ReturnCodeEnum.ERROR, ReturnCodeEnum.ERROR.getDesc());
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success(response);
  }

  @Override
  public ReturnResult<AccountRegisterResponse> syncAccount(
      @Validated @RequestBody AccountSyncRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("同步信息", "账号信息");
    AccountSyncResponse response = null;
    try {
      response = accountSyncService.syncAccount(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("syncAccount 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("syncAccount 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success(response);
  }
}