package cn.org.bjca.footstone.usercenter.api.enmus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/15
 * @since 1.0
 */
@Slf4j
public enum UserInfoStatusEnum {
  NORMAL, //正常
  FREEZE, //冻结
  INVALID; //注销


  @JsonCreator
  public static UserInfoStatusEnum forValue(String value) {
    try {
      return UserInfoStatusEnum.valueOf(value);
    } catch (IllegalArgumentException e) {
      log.error("错误枚举值", value);
      return null;
    }
  }
}
