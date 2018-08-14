package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.facade.EntInfoFacade;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoCheakRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.biz.EntInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/

@RestController
public class EntInfoController implements EntInfoFacade {

  @Autowired
  EntInfoService entInfoService;

  @Override
  public ReturnResult addEntInfo(EntInfoRequest entInfoRequest) {
    return null;
  }

  @Override
  public ReturnResult checkRealEntInfo(EntInfoCheakRequest entInfoCheakRequest) {
    return null;
  }
}
