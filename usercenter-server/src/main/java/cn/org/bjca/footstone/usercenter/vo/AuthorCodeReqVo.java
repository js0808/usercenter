package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @Description:
 * @Author:baoqb
 * @Date:18/8/14
 */
@Data
public class AuthorCodeReqVo extends BaseParam {

  public String mobile;
  public String transId;
  public String templateId;
  public String param;
  public String extension;
}
