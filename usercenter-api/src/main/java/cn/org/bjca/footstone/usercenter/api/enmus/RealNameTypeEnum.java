package cn.org.bjca.footstone.usercenter.api.enmus;

/**
 * @description:实名认证方式
 * @author: ZHAOZHIWEI
 * @create: 2018/8/14
 **/
public enum RealNameTypeEnum {
  NAME_AND_ID_NUM("姓名和身份证号"),
  NAME_AND_MOBILE("姓名和手机号"),
  ID_CARD_AND_FACE("身份证和人脸"),
  BANK_CARD3("银行卡号3要素"),
  BANK_CARD4("银行卡号4要素"),
  ENT_BASE("企业基本信息");

  /**
   * The desc.
   */
  private final String desc;

  RealNameTypeEnum(final String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
