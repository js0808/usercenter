package cn.org.bjca.footstone.usercenter.api.enmus;

import java.util.HashSet;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
public enum LoginChannelEnum {

  WEB("0", "WEB"),
  WAP("1", "WAP"),
  ANDROID("2", "Android安卓"),
  IOS("3", "IOS苹果"),
  OTHER("99", "其他");

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
  LoginChannelEnum(String value, String desc) {
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
    for (LoginChannelEnum enumItem : LoginChannelEnum.values()) {
      if (value.equals(enumItem.value)) {
        return true;
      }
    }
    return false;
  }

  public static LoginChannelEnum findByValue(String value) {
    for (LoginChannelEnum test : LoginChannelEnum.values()) {
      if (value.equals(test.value)) {
        return test;
      }
    }
    return null;
  }

  public static LoginChannelEnum findByName(String name) {
    for (LoginChannelEnum test : LoginChannelEnum.values()) {
      if (test.name().equals(name)) {
        return test;
      }
    }
    return null;
  }
}
