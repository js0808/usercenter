package cn.org.bjca.footstone.usercenter.biz.vo.request;

import cn.org.bjca.footstone.usercenter.biz.vo.base.BaseParam;
import lombok.Data;

@Data
public class CodeValidateSend extends BaseParam {

    private String mobile;
    private String transId;
    private String templateId;
    private String authCode;
}
