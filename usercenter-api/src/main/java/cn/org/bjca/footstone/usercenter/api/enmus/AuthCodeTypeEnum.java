
package cn.org.bjca.footstone.usercenter.api.enmus;


import java.util.HashSet;

/**
 * @author baoqingbin
 * @Description: 验证码类型
 */
public enum AuthCodeTypeEnum {

    REGIST("REGIST", "用户注册"),
    LOGIN("LOGIN", "用户登录"),
    RESET("RESET", "密码重置");

    /**
     * The value.
     */
    private final String value;

    /**
     * The desc.
     */
    private final String desc;

    /**
     * Instantiates a new return status enum.
     *
     * @param value the value
     * @param desc  the desc
     */
    private AuthCodeTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String value() {
        return value;
    }

    /**
     * Gets the desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    public static HashSet<String> hashSet;

    static {
        hashSet = new HashSet<String>();
        hashSet.clear();
        for (AuthCodeTypeEnum codeTypeEnum : AuthCodeTypeEnum.values()) {
            hashSet.add(codeTypeEnum.value());
        }
    }

    public static boolean isDefined(String value) {
        for (AuthCodeTypeEnum enumItem : AuthCodeTypeEnum.values()) {
            if (value.equals(enumItem.value)) {
                return true;
            }
        }
        return false;
    }

    public static AuthCodeTypeEnum findByValue(String value) {
        for (AuthCodeTypeEnum test : AuthCodeTypeEnum.values()) {
            if (value.equals(test.value)) {
                return test;
            }
        }
        return null;
    }

    public static AuthCodeTypeEnum findByName(String name) {
        for (AuthCodeTypeEnum test : AuthCodeTypeEnum.values()) {
            if (test.name().equals(name)) {
                return test;
            }
        }
        return null;
    }
}
