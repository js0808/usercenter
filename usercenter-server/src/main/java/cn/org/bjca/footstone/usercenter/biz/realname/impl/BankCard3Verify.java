package cn.org.bjca.footstone.usercenter.biz.realname.impl;

import static cn.org.bjca.footstone.usercenter.api.commons.Conts.REALNAME_RESULT_SAME;
import static cn.org.bjca.footstone.usercenter.api.commons.Conts.REALNAME_RESULT_SUCCESS;

import cn.org.bjca.footstone.usercenter.biz.realname.RealNameVerify;
import cn.org.bjca.footstone.usercenter.util.IdcardUtil;
import com.google.common.base.Strings;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * { "userName": "bjca", "password": 111111, "name": "test2", "idNumber": 110105198803256114,
 * "accountNo":6228480018475627479, "transactionId": "fafd4b7ae2154f879a96c585513713fc" }
 *
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component("bank_card3")
public class BankCard3Verify extends RealNameVerify {

  static String path = "/idservice/bankcard/checkBankcardThree";

  @Override
  public Pair<Boolean, String> checkRequest() {
    if (Strings.isNullOrEmpty(getUserInfoVo().getName())) {
      return Pair.of(false, "用户姓名不能为空");
    }

    if (!IdcardUtil.isIdcard(getUserInfoVo().getIdNum())) {
      return Pair.of(false, "错误的身份证号码");
    }
    if (Strings.isNullOrEmpty(getUserInfoVo().getBankCardNum())) {
      return Pair.of(false, "错误的银行卡号");
    }

    return Pair.of(true, "");
  }

  @Override
  protected Map<String, String> createParam() {
    Map<String, String> param = super.createParam();
    param.put("name", getUserInfoVo().getName());
    param.put("accountNo", getUserInfoVo().getBankCardNum());
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
    if (Objects.equals(result.get("accountNoResultMsg"), REALNAME_RESULT_SAME) &&
        Objects.equals(result.get("accountNoResult"), REALNAME_RESULT_SUCCESS)) {
      return judge;
    }
    return Pair.of(false, "验证结果:" + result.get("accountNoResultMsg"));
  }
}
