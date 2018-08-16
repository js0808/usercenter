package cn.org.bjca.footstone.usercenter.api.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LvYong
 * @create 2018/8/16
 **/
@Data
@AllArgsConstructor
public class LoginResponse {

  private String token;
  private long expire;

}
