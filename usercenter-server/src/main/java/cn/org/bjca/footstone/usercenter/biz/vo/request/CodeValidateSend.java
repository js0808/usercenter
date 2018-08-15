package cn.org.bjca.footstone.usercenter.biz.vo.request;

import lombok.Data;

@Data
public class CodeValidateSend {

    private String appId;
    private String mobile;
    private String transId;
    private String templateId;
    private String authCode;
}
