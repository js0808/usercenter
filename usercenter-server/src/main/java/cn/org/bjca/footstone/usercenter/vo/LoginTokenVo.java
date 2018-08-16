package cn.org.bjca.footstone.usercenter.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Data
@AllArgsConstructor(staticName = "of")
public class LoginTokenVo {

  private String username;
  private String token;
  private long expire;
  private int timeoutMinutes;

}
