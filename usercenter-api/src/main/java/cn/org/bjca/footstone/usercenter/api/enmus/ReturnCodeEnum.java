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

  REALNAME_TYPE_ERROR(60604006, "错误的实名认证方式"),

  REALNAME_NOT_EXIST(60604007, "未找到实名认证信息uid {0}"),

  REALNAME_PARAM_ERROR(60604008, "实名认证参数错误 {0}"),

  ID_SERVICE_ERROR(60604009, "身份核实错误[%s]"),

  ID_SERVICE_CONN_ERROR(60604010, "身份核实服务通信异常"),

  MSG_SERVER_ERROR(60604011, "短信消息服务异常: %s");

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