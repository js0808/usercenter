package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

@Data
public class CodeValidateReqVo extends BaseParam {

  private String mobile;
  private String transId;
  private String templateId;
  private String authCode;
}
