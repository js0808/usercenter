package cn.org.bjca.footstone.usercenter.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * health check head return 200
 *
 * @author LvYong
 * @create 2018/7/26
 **/
@RestController
public class HealthController {

  @RequestMapping(value = "/", method = RequestMethod.HEAD)
  public void head() {

  }

}