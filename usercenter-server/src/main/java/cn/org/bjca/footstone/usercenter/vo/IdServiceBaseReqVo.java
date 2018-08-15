package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/14
 **/
@Data
public class IdServiceBaseReqVo {

  private String userName = null;
  private String password = null;
  private String transactionId = null;
}
