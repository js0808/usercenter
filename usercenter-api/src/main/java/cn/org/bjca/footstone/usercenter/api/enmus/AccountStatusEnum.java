
package cn.org.bjca.footstone.usercenter.api.enmus;


import java.util.HashSet;

/**
 * @author baoqingbin
 * @Description: 帐号状态
 */
public enum AccountStatusEnum {

    NORMAL("NORMAL", "正常"),
    FREEZE("FREEZE", "冻结"),
    INVALID("INVALID", "注销");

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
    private AccountStatusEnum(String value, String desc) {
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
        for (AccountStatusEnum typeEnum : AccountStatusEnum.values()) {
            hashSet.add(typeEnum.value());
        }
    }

    public static boolean isDefined(String value) {
        for (AccountStatusEnum enumItem : AccountStatusEnum.values()) {
            if (value.equals(enumItem.value)) {
                return true;
            }
        }
        return false;
    }

    public static AccountStatusEnum findByValue(String value) {
        for (AccountStatusEnum test : AccountStatusEnum.values()) {
            if (value.equals(test.value)) {
                return test;
            }
        }
        return null;
    }

    public static AccountStatusEnum findByName(String name) {
        for (AccountStatusEnum test : AccountStatusEnum.values()) {
            if (test.name().equals(name)) {
                return test;
            }
        }
        return null;
    }
}
