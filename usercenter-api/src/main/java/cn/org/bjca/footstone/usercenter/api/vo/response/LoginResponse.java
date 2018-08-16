package cn.org.bjca.footstone.usercenter.api.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LvYong
 * @create 2018/8/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class LoginResponse {

  private Long uid;
  private String token;
  private long expire;
  private AccountInfoResponse accountInfoResponse;

}