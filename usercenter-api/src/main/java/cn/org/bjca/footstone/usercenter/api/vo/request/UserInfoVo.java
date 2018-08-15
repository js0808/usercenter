package cn.org.bjca.footstone.usercenter.api.vo.request;

import lombok.Data;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/13
 * @since 1.0
 */
@Data
public class UserInfoVo {

  private String headImgUrl;

  private String name;

  private String idType;

  private String idNum;

  private String mobile;

  private String email;

  private String bankCardNum;

  private String faceIdImageUrl;

  private String idCardFrontImageUrl;

  private String idCardBackImageUrl;

  private String fromPlatform;

  private String realNameType;

}
