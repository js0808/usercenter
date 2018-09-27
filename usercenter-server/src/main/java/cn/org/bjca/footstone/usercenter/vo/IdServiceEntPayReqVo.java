package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @description: 发起企业打款
 * @author: ZHAOZHIWEI
 * @create: 2018/8/14
 **/
@Data
public class IdServiceEntPayReqVo extends IdServiceBaseReqVo {

  private String account = null;
  private String accountName = null;
  private String accountBank = null;
  private String accountAddressCode = null;
}
