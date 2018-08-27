
package cn.org.bjca.footstone.usercenter.api.enmus;


import java.util.HashSet;

/**
 * @author baoqingbin
 * @Description: 帐号类型
 */
public enum AccountTypeEnum {

  MOBILE("MOBILE", "手机号"),
  EMAIL("EMAIL", "邮箱");

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
   * @param desc the desc
   */
  private AccountTypeEnum(String value, String desc) {
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
    for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
      hashSet.add(accountTypeEnum.value());
    }
  }

  public static boolean isDefined(String value) {
    for (AccountTypeEnum enumItem : AccountTypeEnum.values()) {
      if (value.equals(enumItem.value)) {
        return true;
      }
    }
    return false;
  }

  public static AccountTypeEnum findByValue(String value) {
    for (AccountTypeEnum test : AccountTypeEnum.values()) {
      if (value.equals(test.value)) {
        return test;
      }
    }
    return null;
  }

  public static AccountTypeEnum findByName(String name) {
    for (AccountTypeEnum test : AccountTypeEnum.values()) {
      if (test.name().equals(name)) {
        return test;
      }
    }
    return null;
  }
}
