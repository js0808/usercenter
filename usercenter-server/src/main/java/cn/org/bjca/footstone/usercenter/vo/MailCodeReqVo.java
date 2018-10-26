package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

@Data
public class MailCodeReqVo {

  private String subject;

  private String to;

  private String fromName;

  private String plainText;

  private String htmlText;

  private String trace_id;

  private String third_trace_id;

  private String appId;

  private String templateId;

  private String deviceId;
}
