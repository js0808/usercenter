package cn.org.bjca.footstone.usercenter.api.enmus;

/**
 * 通用返回码
 *
 * @author LvYong
 * @create 2017-08-16
 **/
public enum ReturnCodeEnum {

  SUCCESS(200, "成功"),
  ERROR(60609999, "服务端异常"),

  REQ_PARAM_ERR(60604001, "请求参数错误"),
  SQL_EXCEPTION(60604002, "操作数据库异常"),
  APPID_NOT_EXIST(60604003, "接入方ID不存在"),
  APP_INFO_PUBKEY_ERROR(60604004, "获得接入方公钥错误"),
  SIGN_VERIFY_ERROR(60604005, "验证请求签名错误"),
  RESOURCE_NOT_EXIST(60604006, "查询信息不存在"),
  RESOURCE_ALREADY_EXIST(60604007, "信息已经存在"),
  REALNAME_TYPE_ERROR(60604008, "错误的实名认证方式"),
  REALNAME_NOT_EXIST(60604009, "未找到实名认证信息uid: %s"),
  REALNAME_PERSON_ERROR(60604010, "个人实名认证信息异常: %s"),
  REALNAME_PARAM_ERROR(60604011, "实名认证参数错误 %s"),
  USER_STATUS_ERROR(60604012, "个人用户信息状态异常"),
  ID_SERVICE_ERROR(60604013, "身份核实错误: %s"),
  ID_SERVICE_CONN_ERROR(60604014, "身份核实服务通信异常"),
  MSG_SERVER_ERROR(60604015, "短信消息服务异常: %s"),
  AUTH_CODE_NOT_EXIT_ERROR(60604016, "验证码不存在或已过期"),
  AUTH_CODE_VALIDATE_ERROR(60604017, "验证码验证失败"),
  ACCOUNT_EXIT_ERROR(60604018, "帐号已经存在"),
  ACCOUNT_NOT_EXIT_ERROR(60604019, "帐号不存在"),
  USER_OR_PWD_ERROR(60604020, "用户名或密码不正确"),
  USER_IS_LOCKED(60604021, "用户被锁定"),
  USER_TOKEN_WRONG(60604022, "用户TOKEN不正确"),
  UID_NULL_ERROR(60604023, "UID不能为空"),
  ENT_INFO_STATUS_ERROR(60604024, "企业信息状态异常"),
  VALIDATE_ID_NOT_EXIT_ERROR(60604025, "验证标识错误"),
  USER_STATUS_WRONG(60604026, "用户已冻结或注销"),
  PATCHCA_GENERATE_ERROR(60604040, "生成验证码异常"),
  PATCHCA_VALIDATE_ERROR(60604041, "验证码验证错误"),
  PATCHCA_EXPIRE_ERROR(60604042, "验证码过期"),
  SIGN_SERVICE_CONN_ERROR(60604043, "签名服务通信异常"),
  VERIFY_SIGN_CERT_ERROR(60604044, "验签名或证书失败: %s"),
  PARSE_CERT_UID_ERROR(60604045, "获取证书唯一标识失败: %s"),
  CERT_EXIST_ERROR(60604046, "证书已经注册"),
  IMAGES_UPLOAD_LOCAL_ERROR(60604047, "图片转存本地异常"),
  IMAGES_UPLOAD_READ_ERROR(60604048, "图片读取异常"),
  IMAGES_UPLOAD_REMOTE_ERROR(60604049, "上传文件服务器异常"),
  IMAGES_DOWNLOAD_REMOTE_ERROR(60604050, "下载文件异常"),
  REAL_NAME_VERIFY_REQ_NOT_EXIST(60604051, "身份核实请求不存在"),
  USER_NOT_EXIST(60604052, "用户不存在"),
  CERT_NOT_REGISTE(60604053, "证书未注册"),
  ALREADY_REAL_NAME_CHECKED(60604054, "用户名或企业名称已经实名认证");

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