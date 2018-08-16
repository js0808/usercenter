package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.facade.AuthCodeFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EmailCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeApplyResponse;
import cn.org.bjca.footstone.usercenter.biz.AuthCodeService;
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
public class AuthCodeController implements AuthCodeFacade {

  @Autowired
  private AuthCodeService authCodeService;

  @Override
  public ReturnResult<AuthCodeApplyResponse> codeApply(@Validated @RequestBody AuthCodeApplyRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("用户中心服务器", "验证码申请", "短信验证码申请交易");
    AuthCodeApplyResponse response = new AuthCodeApplyResponse();
    try {
      response = authCodeService.codeApply(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("codeAppley 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("codeAppley 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success(response);
  }

  @Override
  public ReturnResult emailCodeApply(@Validated @RequestBody EmailCodeApplyRequest request) {
    MetricsClient metrics = MetricsClient.newInstance("用户中心服务器", "验证码申请", "邮件验证码申请交易");
    try {
      authCodeService.emailCodeApply(request);
      metrics.sr_incrSuccess();
    } catch (BjcaBizException ex) {
      log.error("emailCodeApply 异常信息", ex);
      throw ex;
    } catch (Exception e) {
      log.error("emailCodeApply 异常信息", e);
      throw new BjcaBizException(ReturnCodeEnum.ERROR);
    } finally {
      metrics.qps().rt().sr_incrTotal();
    }
    return ReturnResult.success("success");
  }
}