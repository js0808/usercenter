package cn.org.bjca.footstone.usercenter.api.vo.response;

import java.util.Date;
import lombok.Data;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/17
 **/
@Data
public class QueryEntInfoResponse {

  private Integer id;

  private Long uid;

  private String headImgUrl;

  private String name;

  private String phone;

  private String orgCode;

  private String bizLicense;

  private String socialCreditCode;

  private String legalName;

  private String legalIdNum;

  private Integer realNameFlag;

  private String realNameType;

  private Integer reviewFlag;

  private String bizLicenseImageUrl;

  private String orgCodeImageUrl;

  private String appId;

  private String status;

  private String extField1;

  private String extField2;

  private String extField3;

  private String extField4;

  private Integer version;

  private String oper;

  private Date createTime;

  private Date updateTime;
}
