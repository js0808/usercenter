package cn.org.bjca.footstone.usercenter.api.vo.response;

import lombok.Data;

/**
 * 检查账号存在时的账号信息
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Data
public class AccountInfoCheckResponse {

  private String accountType;

  private String account;

  private String status;

  private Long uid;

  private String userType;

}
