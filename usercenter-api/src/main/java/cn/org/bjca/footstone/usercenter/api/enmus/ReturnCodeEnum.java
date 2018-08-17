package cn.org.bjca.footstone.usercenter.api.enmus;

/**
 * 通用返回码
 *
 * @author LvYong
 * @create 2017-08-16
 **/
public enum ReturnCodeEnum {

  SUCCESS(200, "成功"),

  REQ_PARAM_ERR(60604001, "请求参数错误"),

  SQL_EXCEPTION(60604002, "操作数据库异常"),

  APPID_NOT_EXIST(60604003, "接入方ID不存在"),

  APP_INFO_PUBKEY_ERROR(60604004, "获得接入方公钥错误"),

  SIGN_VERIFY_ERROR(60604005, "验证请求签名错误"),

  ERROR(60609999, "服务端异常"),
  RESOURCE_NOT_EXIST(60609998, "查询信息不存在"),

  REALNAME_TYPE_ERROR(60604026, "错误的实名认证方式"),
  REALNAME_NOT_EXIST(60604027, "未找到实名认证信息uid %s"),
  REALNAME_PERSON_ERROR(10904028, "个人实名认证信息异常 %s"),
  REALNAME_PARAM_ERROR(60604029, "实名认证参数错误 %s"),
  USER_STATUS_ERROR(60604030, "个人用户信息异常"),

  ID_SERVICE_ERROR(60604009, "身份核实错误[%s]"),

  ID_SERVICE_CONN_ERROR(60604010, "身份核实服务通信异常"),

  MSG_SERVER_ERROR(60604011, "短信消息服务异常: %s"),

  AUTH_CODE_NOT_EXIT_ERROR(60604012, "验证码不存在或已过期"),

  AUTH_CODE_VALIDATE_ERROR(60604013, "验证码验证失败"),

  ACCOUNT_EXIT_ERROR(60604014, "帐号已经存在"),

  ACCOUNT_NOT_EXIT_ERROR(60604015, "帐号不存在"),

  USER_OR_PWD_ERROR(60604030, "用户名或密码不正确"),
  USER_IS_LOCKED(60604031, "用户被锁定"),
  USER_TOKEN_WRONG(60604032, "用户TOKEN不正确"),

  ENT_INFO_NOT_ENOUGH(60604012, "统一社会信用代码/工商营业执照号/组织机构号不能同时为空"),
  ENT_INFO_NOT_EXIST(60604013, "企业信息不存在");

  /**
   * The value.
   */
  private final int value;

  /**
   * The desc.
   */
  private final String desc;

  ReturnCodeEnum(final int value, final String desc) {
    this.value = value;
    this.desc = desc;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public int value() {
    return this.value;
  }

  /**
   * Gets the desc.
   *
   * @return the desc
   */
  public String getDesc() {
    return this.desc;
  }

}