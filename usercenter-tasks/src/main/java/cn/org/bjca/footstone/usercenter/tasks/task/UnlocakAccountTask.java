package cn.org.bjca.footstone.usercenter.tasks.task;


import cn.org.bjca.footstone.usercenter.dao.mapper.AccountAttemptsMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapperCustom;
import cn.org.bjca.footstone.usercenter.dao.model.AccountAttemptsExample;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.tasks.lock.JobRunningStatus;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 账号解锁
 */
@Component
@Slf4j
public class UnlocakAccountTask {

  @Autowired
  private AccountInfoMapperCustom accountInfoMapperCustom = null;

  @Autowired
  private AccountInfoMapper accountInfoMapper = null;

  @Autowired
  private AccountAttemptsMapper accountAttemptsMapper = null;

  @Scheduled(cron = "${bjca.task.cron-unlock-account}")
  public void unlockAccount() {
    if (JobRunningStatus.isSuccess()) {
      log.info("unlockAccount开始执行...");
    } else {
      log.info("unlockAccount没有得到锁，无法执行");
      return;
    }

    //查询未发送和发送失败的记录
    Date now = new Date();
    List<AccountInfo> accountInfos = accountInfoMapperCustom.selectLockAccount(new Date());
    log.info("解锁账号，条数[{}]", accountInfos.size());

    for (AccountInfo accountInfo : accountInfos) {
      log.info("解锁账号，账号[{}]，账号类型[{}]，UID[{}],用户类型[{}]", accountInfo.getAccount(),
          accountInfo.getAccountType(), accountInfo.getUid(), accountInfo.getUserType());
      try {
        //解锁
        AccountInfo updateInfo = new AccountInfo();
        updateInfo.setId(accountInfo.getId());
        updateInfo.setIsLocked(false);
        updateInfo.setVersion(accountInfo.getVersion() + 1);
        updateInfo.setUpdateTime(new Date());
        accountInfoMapper.updateByPrimaryKeySelective(updateInfo);
        //删除重试记录
        AccountAttemptsExample example = new AccountAttemptsExample();
        example.createCriteria().andAccountEqualTo(accountInfo.getAccount());
        accountAttemptsMapper.deleteByExample(example);

        log.info("解锁账号成功");
      } catch (Exception e) {
        log.error("解锁账号失败，账号[{}]，账号类型[{}]，UID[{}],用户类型[{}]", accountInfo.getAccount(),
            accountInfo.getAccountType(), accountInfo.getUid(), accountInfo.getUserType());
      }
    }
  }
}
