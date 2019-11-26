package cn.org.bjca.footstone.usercenter.api.vo.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 *
 * description:
 *
 * @author: wanghui<tinyhui.wang at gmail.com>
 * @date: 2019-07-19 15:48
 */

@Data
public class EnterpriseSyncRequest {

  private String headImgUrl;

  /**
   * 企业名称
   */
  @NotBlank(message = "企业名称不能为空")
  private String name;

  private String phone;
  private String orgCode;
  private String bizLicense;
  private String socialCreditCode;
  private String legalName;
  private String legalidNum;

  /**
   * 是否人工审核 0未人工审核、1人工审核
   */

  private Integer reviewFlag;
  private String bizLicenseImageUrl;
  private String orgCodeImageUrl;
  private String legalIdFrontImageUrl;
  private String legalIdBackImageUrl;
  private String accountName;
  private String bankAccount;
  private String bankName;
  private String bankAddressCode;
  private String status;
  @NotBlank(message = "操作人不能为空")
  private String oper;

  @NotBlank(message = "appId不能为空")
  private String appId;


  public static void main(String[] args) throws Exception {
    System.out.println(
        JSON.toJSONString(new EnterpriseSyncRequest(), SerializerFeature.PrettyFormat,
            SerializerFeature.WriteNullStringAsEmpty));
  }
}
