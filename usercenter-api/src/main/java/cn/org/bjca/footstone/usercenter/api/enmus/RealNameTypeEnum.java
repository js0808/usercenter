package cn.org.bjca.footstone.usercenter.api.enmus;

import java.util.HashSet;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/14
 **/
public enum RealNameTypeEnum {
  NAME_AND_ID_NUM("NAME_AND_ID_NUM", "姓名和身份证号"),
  NAME_AND_MOBILE("NAME_AND_MOBILE", "姓名和手机号"),
  ID_CARD_AND_FACE("ID_CARD_AND_FACE", "身份证和人脸"),
  BANK_CARD3("BANK_CARD3", "银行卡号3要素"),
  BANK_CARD4("BANK_CARD4", "银行卡号4要素"),
  ENT_PAY("ENT_PAY", "企业打款");

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
  private RealNameTypeEnum(String value, String desc) {
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
    for (RealNameTypeEnum codeTypeEnum : RealNameTypeEnum.values()) {
      hashSet.add(codeTypeEnum.value());
    }
  }

  public static boolean isDefined(String value) {
    for (RealNameTypeEnum enumItem : RealNameTypeEnum.values()) {
      if (value.equals(enumItem.value)) {
        return true;
      }
    }
    return false;
  }

  public static RealNameTypeEnum findByValue(String value) {
    for (RealNameTypeEnum test : RealNameTypeEnum.values()) {
      if (value.equals(test.value)) {
        return test;
      }
    }
    return null;
  }

  public static RealNameTypeEnum findByName(String name) {
    for (RealNameTypeEnum test : RealNameTypeEnum.values()) {
      if (test.name().equals(name)) {
        return test;
      }
    }
    return null;
  }
}
