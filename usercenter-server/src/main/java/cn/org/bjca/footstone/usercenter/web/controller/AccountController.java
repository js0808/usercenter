package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.facade.AccountInfoFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountChangeRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountRegisterRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountStatusUpdateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ModifyPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.RegisterCertRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ResetPasswordRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountRegisterResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.CertRegisterResponse;
import cn.org.bjca.footstone.usercenter.biz.AccountRegisterService;
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
public class AccountController implements AccountInfoFacade {

  @Autowired
  private AccountRegisterService registerService;

  @Override
  public ReturnResult<AccountRegisterResponse> register(@Validated @RequestBody AccountRegisterRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("对外服务", "帐号注册", "帐号注册交易");
    AccountRegisterResponse registerResponse = new AccountRegisterResponse();
    try {
      registerResponse = registerService.accountRegister(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("accountRegister 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("accountRegister 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success(registerResponse);
  }

  @Override
  public ReturnResult resetPassword(@Validated @RequestBody ResetPasswordRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("对外服务", "密码重置", "密码重置交易");
    try {
      registerService.resetPassword(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("resetPassword 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("resetPassword 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success("success");
  }

  @Override
  public ReturnResult accountStatus(@Validated @RequestBody AccountStatusUpdateRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("对外服务", "帐号状态", "帐号状态变更");
    try {
      registerService.accountStatus(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("accountStatus 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("accountStatus 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success("success");
  }

  @Override
  public ReturnResult modifyPassword(@Validated @RequestBody ModifyPasswordRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("对外服务", "修改密码");
    try {
      registerService.modifyPassword(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("modifyPassword 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("modifyPassword 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success("success");
  }

  @Override
  public ReturnResult accountChange(@Validated @RequestBody AccountChangeRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("对外服务", "登录帐号变更");
    try {
      registerService.accountChange(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("accountChange 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("accountChange 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success("success");
  }

  @Override
  public ReturnResult registerByCert(@Validated @RequestBody RegisterCertRequest request) {
    MetricsClient metricsClient = MetricsClient.newInstance("对外服务", "证书注册");
    try {
      CertRegisterResponse response = registerService.registerByCert(request);
      metricsClient.sr_incrSuccess();
      return ReturnResult.success(response);
    } catch (Exception e) {
      throw e;
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
  }

  @Override
  public ReturnResult checkAccount(AccountInfoRequest accountInfoRequest) {
    return ReturnResult.success(registerService.accountInfo(accountInfoRequest.getAccount()));
  }
}