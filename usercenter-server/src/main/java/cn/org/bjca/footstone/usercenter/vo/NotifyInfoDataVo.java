package cn.org.bjca.footstone.usercenter.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @description:信息变更通知VO
 * @author: ZHAOZHIWEI
 * @create: 2018/8/23
 **/
@Data
@Builder
public class NotifyInfoDataVo {

  private Long uid;

  private String userType;

  private String account;

  private String notifyType;

}
