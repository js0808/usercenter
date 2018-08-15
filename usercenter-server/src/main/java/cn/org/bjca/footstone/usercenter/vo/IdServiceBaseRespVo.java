package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/14
 **/
@Data
public class IdServiceBaseRespVo {

  public static final String OK = "000000";
  private String resultCode = null;
  private String resultMessage = null;
  private String transactionId = null;
}
