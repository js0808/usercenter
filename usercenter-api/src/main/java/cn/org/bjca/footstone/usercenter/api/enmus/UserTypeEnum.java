package cn.org.bjca.footstone.usercenter.api.enmus;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/17
 * @since 1.0
 */
public enum UserTypeEnum {

  USER, //个人
  ENT,   //企业
  ;

  public static UserTypeEnum findByName(String name) {
    for (UserTypeEnum test : UserTypeEnum.values()) {
      if (test.name().equals(name)) {
        return test;
      }
    }
    return null;
  }
}
