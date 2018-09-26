package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @description: 通过验证码验证企业打款
 * @author: ZHAOZHIWEI
 * @create: 2018/8/14
 **/
@Data
public class IdServiceEntPayQueryReqVo extends IdServiceBaseReqVo {

  private String queryTransId = null;
  private String verifyCode = null;
}
