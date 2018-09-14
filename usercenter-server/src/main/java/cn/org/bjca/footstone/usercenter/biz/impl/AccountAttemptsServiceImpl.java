package cn.org.bjca.footstone.usercenter.biz.impl;

import cn.org.bjca.footstone.usercenter.biz.AccountAttemptsService;
import cn.org.bjca.footstone.usercenter.config.AccountLoginConfig;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountAttemptsMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountAttempts;
import cn.org.bjca.footstone.usercenter.dao.model.AccountAttemptsExample;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample.Criteria;
import cn.org.bjca.footstone.usercenter.util.RDate;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Slf4j
@Service
public class AccountAttemptsServiceImpl implements AccountAttemptsService {

  @Autowired
  private AccountAttemptsMapper accountAttemptsMapper;

  @Autowired
  private AccountInfoMapper accountInfoMapper;

  @Autowired
  private AccountLoginConfig accountLoginConfig;

  @Transactional(noRollbackFor = LockedException.class)
  @Override
  public int updateFailAttempts(String username) {
    int maxAttempts = accountLoginConfig.getMaxAttempts();
    AccountAttempts accountAttempts = getAccountAttempts(username);
    if (accountAttempts == null) {
      AccountAttempts record = new AccountAttempts();
      record.setAccount(username);
      record.setAttempts(1);
      record.setLastmodified(new Date());
      accountAttemptsMapper.insertSelective(record);
      return maxAttempts - 1;
    } else {
      AccountAttempts record = new AccountAttempts();
      record.setId(accountAttempts.getId());
      if (RDate.diffminutes(new Date(), accountAttempts.getLastmodified()) > accountLoginConfig
          .getAttemptsDurationMinutes()) {
        record.setAttempts(1);
      } else {
        record.setAttempts(accountAttempts.getAttempts() + 1);
      }
      record.setLastmodified(new Date());
      accountAttemptsMapper.updateByPrimaryKeySelective(record);
      if (accountAttempts.getAttempts() + 1 >= maxAttempts) {
        AccountInfoExample example = new AccountInfoExample();
        Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(username);

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setIsLocked(true);
        accountInfo
            .setLockedExpireTime(RDate.addMinutes(accountLoginConfig.getLockedExpireMinutes()));
        log.info("[LOCKED]:{},{}", username, accountInfo);
        accountInfoMapper.updateByExampleSelective(accountInfo, example);
        throw new LockedException("user is locked");
      } else {
        return maxAttempts - record.getAttempts();
      }
    }
  }

  @Override
  public void resetFailAttempts(String username) {
    AccountAttempts record = new AccountAttempts();
    record.setAccount(username);
    record.setAttempts(0);
    record.setLastmodified(new Date());
    AccountAttemptsExample example = new AccountAttemptsExample();
    AccountAttemptsExample.Criteria criteria = example.createCriteria();
    criteria.andAccountEqualTo(username);
    accountAttemptsMapper.updateByExampleSelective(record, example);
  }

  @Override
  public AccountAttempts getAccountAttempts(String username) {
    AccountAttemptsExample example = new AccountAttemptsExample();
    AccountAttemptsExample.Criteria criteria = example.createCriteria();
    criteria.andAccountEqualTo(username);
    List<AccountAttempts> accountAttempts = accountAttemptsMapper.selectByExample(example);
    return CollectionUtils.isEmpty(accountAttempts) ? null : accountAttempts.get(0);
  }
}
