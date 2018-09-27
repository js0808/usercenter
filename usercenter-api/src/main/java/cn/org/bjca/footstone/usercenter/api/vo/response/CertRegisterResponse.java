package cn.org.bjca.footstone.usercenter.api.vo.response;

import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/9/19
 **/
@Data
@Builder
public class CertRegisterResponse {

  //企业唯一标识作为账号
  private String account;
  private Long uid;
}
