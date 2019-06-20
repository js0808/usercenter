package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.biz.AuthCodeService;
import cn.org.bjca.footstone.usercenter.biz.ImagesService;
import cn.org.bjca.footstone.usercenter.biz.realname.EntRealNameVerify;
import cn.org.bjca.footstone.usercenter.biz.realname.impl.BankCard4Verify;
import cn.org.bjca.footstone.usercenter.biz.signverify.VerifySignCertService;
import cn.org.bjca.footstone.usercenter.config.AccountLoginConfig;
import cn.org.bjca.footstone.usercenter.config.AuthCodeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baoqingbin
 * @create 2018/8/14
 **/
@RestController
@Slf4j
public class ApolloTestController {

  @Autowired
  private AuthCodeConfig authCodeConfig;
  @Autowired
  private AccountLoginConfig accountLoginConfig;
  @Autowired
  private EntRealNameVerify entRealNameVerify;
  @Autowired
  private BankCard4Verify bankCard4Verify;
  @Autowired
  private VerifySignCertService verifySignCertService;
  @Autowired
  private ImagesService imagesService;
  @Autowired
  private AuthCodeService authCodeService;

  @RequestMapping(value = "/apollotest", method = RequestMethod.POST)
  @ResponseBody
  public ReturnResult test() {
    return ReturnResult.success("success");
  }

}