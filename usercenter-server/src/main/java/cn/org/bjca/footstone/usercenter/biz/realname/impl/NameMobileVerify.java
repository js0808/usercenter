package cn.org.bjca.footstone.usercenter.biz.realname.impl;

import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import cn.org.bjca.footstone.usercenter.util.IdcardUtil;
import com.google.common.base.Strings;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * { "userName": "bjca", "password": 111111, "name": "test2", "idNumber": 110105198803256114,
 * "mobile": 13811339388, "transactionId": "fafd4b7ae2154f879a96c585513713fc" }
 *
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component("name_and_mobile")
public class NameMobileVerify extends RealNameVerify {

  private static final String PATTERN = "^\\d{11}$";

  @Override
  public Pair<Boolean, String> checkRequest() {
    if (Strings.isNullOrEmpty(getUserInfoVo().getName())) {
      return Pair.of(false, "用户姓名不能为空");
    }

    if (!IdcardUtil.isIdcard(getUserInfoVo().getIdNum())) {
      return Pair.of(false, "错误的身份证号码");
    }
    String mobile = getUserInfoVo().getMobile();
    if (Strings.isNullOrEmpty(mobile) || !Pattern.matches(mobile, PATTERN)) {
      return Pair.of(false, "错误的手机号码");
    }
    return Pair.of(true, "");
  }
}
