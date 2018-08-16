package cn.org.bjca.footstone.usercenter.api.vo.response;

import java.util.Date;
import lombok.Data;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Data
public class AccountInfoResponse {

  private Long uid;
  private String accountType;
  private String account;
  private String status;
  private String userType;
  private Date lastLoginTime;
  private Integer version;

}