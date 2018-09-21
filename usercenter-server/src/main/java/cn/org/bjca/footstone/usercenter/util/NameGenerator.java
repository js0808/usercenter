package cn.org.bjca.footstone.usercenter.util;

import java.util.UUID;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/7
 * @since 1.0
 */
public abstract class NameGenerator {

  public static String genera() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
