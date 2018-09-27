package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @description:调用基础签名服务验证其阿明同时验证证书
 * @author: ZHAOZHIWEI
 * @create: 2018/9/18
 **/
@Data
public class GetCertUidReqVo {

  private String transId = null;
  private String appId = null;
  private String base64Cert = null;
}
