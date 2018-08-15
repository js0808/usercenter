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
 * { "userName": "bjca", "password": "bjca", "name": "test2", "idNumber": 110101197906048860,
 * "userPhoto": "base64", "transactionId": "123456" }
 *
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component("id_card_and_face")
public class IdFaceVerify extends RealNameVerify {

  private static final String PATTERN = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

  @Override
  public Pair<Boolean, String> checkRequest() {
    if (Strings.isNullOrEmpty(getUserInfoVo().getName())) {
      return Pair.of(false, "用户姓名不能为空");
    }

    if (!IdcardUtil.isIdcard(getUserInfoVo().getIdNum())) {
      return Pair.of(false, "错误的身份证号码");
    }
    String faceIdImageUrl = getUserInfoVo().getFaceIdImageUrl();
    if (Strings.isNullOrEmpty(faceIdImageUrl) || !Pattern.matches(PATTERN, faceIdImageUrl)) {
      return Pair.of(false, "错误的照片信息");
    }
    return Pair.of(true, "");
  }
}
