package cn.org.bjca.footstone.usercenter.biz.listener;

import cn.org.bjca.footstone.usercenter.biz.AccountAttemptsService;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.LoginLogMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.LoginLog;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Slf4j
@Component
public class LoginListener {

  @Autowired
  private AccountAttemptsService accountAttemptsService;
  @Autowired
  private LoginLogMapper loginLogMapper;
  @Autowired
  private AccountInfoMapper accountInfoMapper;

  @Async
  @EventListener
  public void login(LoginEvent loginEvent) {
    log.debug("logevent:{}", loginEvent);
    if (loginEvent.isLoginSuccess()) {
      accountAttemptsService.resetFailAttempts(loginEvent.getAccount());
    }
    LoginLog record = new LoginLog();
    record.setAccount(loginEvent.getAccount());
    record.setClientInfo(loginEvent.getRequest().getClientInfo());
    record.setLoginResult(loginEvent.isLoginSuccess());
    AccountInfo accountInfo = loginEvent.getAccountInfo();
    if (accountInfo != null) {
      record.setUid(accountInfo.getUid());
      record.setUserType(accountInfo.getUserType());
      record.setAccountType(accountInfo.getAccountType());
      record.setCreateTime(new Date());
    }
    loginLogMapper.insertSelective(record);

    if (loginEvent.isLoginSuccess() && accountInfo != null) {
      AccountInfo modAccountInfo = new AccountInfo();
      modAccountInfo.setId(accountInfo.getId());
      modAccountInfo.setLastLoginTime(new Date());
      modAccountInfo.setIsLocked(false);
      accountInfoMapper.updateByPrimaryKeySelective(modAccountInfo);
    }
  }
}
