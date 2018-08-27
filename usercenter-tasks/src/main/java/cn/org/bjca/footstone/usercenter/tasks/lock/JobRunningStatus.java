package cn.org.bjca.footstone.usercenter.tasks.lock;

import org.slf4j.LoggerFactory;

public class JobRunningStatus {

  private static boolean success = true;

  public static boolean isSuccess() {
    return success;
  }

  public static void setSuccess(boolean success) {
    JobRunningStatus.success = success;
    LoggerFactory.getLogger(JobRunningStatus.class).debug("设置定时任务执行标志为：{}", success);
  }

}
