package cn.org.bjca.footstone.usercenter.tasks.task;


import cn.org.bjca.footstone.usercenter.dao.mapper.NotifyInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.NotifyInfo;
import cn.org.bjca.footstone.usercenter.tasks.lock.JobRunningStatus;
import cn.org.bjca.footstone.usercenter.tasks.util.KafkaProducerClient;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 用户信息新增、变更通知
 */
@Component
@Slf4j
public class NotifyTask {

  private static final String NOTIFY_FAIL = "FAIL";
  private static final String NOTIFY_SUCCESS = "SUCCESS";

  @Value("${kafka.topic-notify}")
  private String topicNotify = null;
  @Value("${bjca.task.notify-max-num}")
  private int notifyMaxNum = 5;

  @Autowired
  private NotifyInfoMapper notifyInfoMapper;

  @Autowired
  private KafkaProducerClient kafkaProducerClient;

  @Scheduled(cron = "${bjca.task.cron-notify}")
  public void doNotify() {
    if (JobRunningStatus.isSuccess()) {
      log.info("doNotify开始执行...");
    } else {
      log.info("doNotify没有得到锁，无法执行");
      return;
    }

    //查询未发送和发送失败的记录
    Date now = new Date();
    //查询待通知记录
    List<NotifyInfo> notifyInfos = null;
    log.info("发送用户中心通知，条数[{}]", notifyInfos.size());

    for (NotifyInfo notifyInfo : notifyInfos) {
      log.info("发送用户中心通知，UID[{}],用户类型[{}],通知类型[{}]", notifyInfo.getUid(), notifyInfo.getUserType(),
          notifyInfo.getNotifyType());

      if (notifyInfo.getNotifyMaxNum() >= notifyMaxNum) {
        NotifyInfo failNotify = new NotifyInfo();
        failNotify.setId(notifyInfo.getId());
        failNotify.setNotifyStatus(NOTIFY_FAIL);
        notifyInfoMapper.updateByPrimaryKeySelective(failNotify);
        continue;
      }

      boolean success = kafkaProducerClient.sendMessage(topicNotify, notifyInfo.getNotifyMsg());
      if (success) {
        NotifyInfo successNotify = new NotifyInfo();
        successNotify.setId(notifyInfo.getId());
        successNotify.setNotifyNum(notifyInfo.getNotifyNum() + 1);
        successNotify.setNotifyStatus(NOTIFY_SUCCESS);
        notifyInfoMapper.updateByPrimaryKeySelective(successNotify);
        log.info("发送用户中心通知成功");
      } else {
        NotifyInfo failNotify = new NotifyInfo();
        failNotify.setId(notifyInfo.getId());
        failNotify.setNotifyNum(notifyInfo.getNotifyNum() + 1);
        log.error("发送用户中心通知，UID[{}],用户类型[{}],通知类型[{}]", notifyInfo.getUid(),
            notifyInfo.getUserType(), notifyInfo.getNotifyType());
      }
    }
  }
}
