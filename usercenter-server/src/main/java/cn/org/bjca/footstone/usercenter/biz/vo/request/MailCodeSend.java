package cn.org.bjca.footstone.usercenter.biz.vo.request;

import lombok.Data;

@Data
public class MailCodeSend {

    private String subject;

    private String to;

    private String fromName;

    private String plainText;

    private String htmlText;
}
