package cn.org.bjca.footstone.usercenter.api.vo.response;

import java.util.Date;
import lombok.Data;

@Data
public class QueryUserInfoResponse {

  private Long uid;

  private String headImgUrl;

  private String name;

  private String idType;

  private String idNum;

  private String mobile;

  private String email;

  private Integer realNameFlag;

  private String realNameType;

  private Boolean reviewFlag;

  private String bankCardNum;

  private String faceIdImageUrl;

  private String idCardFrontImageUrl;

  private String idCardBackImageUrl;

  private String appId;

  private String status;

  private Integer version;

  private Date createTime;

  private Date updateTime;

  private String oper;

}