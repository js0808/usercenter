package cn.org.bjca.footstone.usercenter.api.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Data
@AllArgsConstructor(staticName = "of")
public class AccountCheckResponse {

  private boolean isExist;
  private String account;
}