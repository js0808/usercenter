package cn.org.bjca.footstone.usercenter.biz.realname.impl;

import static cn.org.bjca.footstone.usercenter.api.commons.Conts.REALNAME_FACE_RESULT_SUCCESS;
import static cn.org.bjca.footstone.usercenter.api.commons.Conts.REALNAME_RESULT_SAME;

import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import cn.org.bjca.footstone.usercenter.util.Base64;
import cn.org.bjca.footstone.usercenter.util.IdcardUtil;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import java.util.Map;
import java.util.Objects;
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

  static String path = "/idservice/identity/checkIdface";

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

  @Override
  protected Map<String, String> createParam() {
    Map<String, String> param = super.createParam();
    param.put("name", getUserInfoVo().getName());
    String faceIdImageUrl = getUserInfoVo().getFaceIdImageUrl();
    byte[] images = RestUtils.downLoad(faceIdImageUrl);
    param.put("userPhoto", Base64.encode(images));
    param.put("idNumber", getUserInfoVo().getIdNum());
    return param;
  }

  @Override
  protected String getUrl() {
    return host + path;
  }

  @Override
  protected Pair<Boolean, String> judgeSuccess(Map<String, String> result) {
    Pair<Boolean, String> judge = super.judgeSuccess(result);
    if (!judge.getKey()) {
      return judge;
    }
    if (Objects.equals(result.get("identityResult"), REALNAME_RESULT_SAME) &&
        Objects.equals(result.get("faceResult"), REALNAME_FACE_RESULT_SUCCESS)) {
      return judge;
    }
    return Pair.of(false, "验证结果:" + JSON.toJSONString(result));
  }
}
