package cn.org.bjca.footstone.usercenter.api.vo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 *
 * description:
 *
 * @author: wanghui<tinyhui.wang at gmail.com>
 * @date: 2019-07-19 15:51
 */

@Data
public class AccountSyncRequest {

  /**
   * 企业id
   */
  @NotBlank(message = "企业id不能为空")
  private Integer eid;

  /**
   * 账号类型
   *
   * @see cn.org.bjca.footstone.usercenter.api.enmus.AccountTypeEnum
   */
  @NotBlank(message = "账号类型不能为空")
  private String accountType;

  /**
   * 账号（手机、邮箱、证书、nickname）
   */
  @NotBlank(message = "账号未能为空")
  private String account;

  /**
   * 密码，明文
   */
  @NotBlank(message = "密码不能为空")
  private String password;

  /**
   * 账号状态
   *
   * @see cn.org.bjca.footstone.usercenter.api.enmus.AccountTypeEnum
   */

  @NotBlank(message = "账号状态不能为空")
  private String status;

  /**
   * @see cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum
   */
  @NotBlank(message = "用户类型不能为空")
  private String userType;

  @NotBlank(message = "应用ID不能为空")
  @Length(max = 64)
  private String appId;

}
