package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.dao.model.AccountAttempts;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
public interface AccountAttemptsService {

  int updateFailAttempts(String username);

  void resetFailAttempts(String username);

  AccountAttempts getAccountAttempts(String username);
}
