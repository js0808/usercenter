package cn.org.bjca.footstone.usercenter.api.enmus;

/**
 * 通用返回码
 *
 * @author LvYong
 * @create 2017-08-16
 **/
public enum ReturnCodeEnum {

    SUCCESS(200, "成功"),

    REQ_PARAM_ERR(10904001, "请求参数错误"),

    SQL_EXCEPTION(10904002, "操作数据库异常"),

    APPID_NOT_EXIST(10904003, "接入方ID不存在"),

    APP_INFO_PUBKEY_ERROR(10904004, "获得接入方公钥错误"),

    SIGN_VERIFY_ERROR(10904005, "验证请求签名错误"),

    ERROR(10909999, "服务端异常");


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
