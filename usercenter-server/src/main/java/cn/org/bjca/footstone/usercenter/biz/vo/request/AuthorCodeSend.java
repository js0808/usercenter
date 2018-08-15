package cn.org.bjca.footstone.usercenter.biz.vo.request;

import cn.org.bjca.footstone.usercenter.biz.vo.base.BaseParam;
import lombok.Data;

/**
 * @Description:
 * @Author:baoqb
 * @Date:18/8/14
 */
@Data
public class AuthorCodeSend extends BaseParam{
    public String mobile;
    public String transId;
    public String templateId;
    public String param;
    public String extension;
}
