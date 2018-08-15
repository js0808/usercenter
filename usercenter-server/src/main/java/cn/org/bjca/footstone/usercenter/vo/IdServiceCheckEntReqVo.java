package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/14
 **/
@Data
public class IdServiceCheckEntReqVo extends IdServiceBaseReqVo {

  private String enterpriseName = null;
  private String unitCode = null;
  private String unCreditCode = null;
  private String businessLicenseNo = null;
  private String leagalPerson = null;
  private String keywordType = null;
}
