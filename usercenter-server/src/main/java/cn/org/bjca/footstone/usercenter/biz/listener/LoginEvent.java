package cn.org.bjca.footstone.usercenter.biz.listener;

import cn.org.bjca.footstone.usercenter.api.vo.request.LoginRequest;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Data
@AllArgsConstructor(staticName = "of")
public class LoginEvent {

  private boolean isLoginSuccess;
  private String account;
  private LoginRequest request;
  private AccountInfo accountInfo;

}
