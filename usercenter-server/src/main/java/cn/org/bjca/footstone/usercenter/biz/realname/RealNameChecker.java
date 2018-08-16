package cn.org.bjca.footstone.usercenter.biz.realname;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * NAME_AND_ID_NUM 姓名和身份证号
 *
 * NAME_AND_MOBILE 姓名和手机号
 *
 * ID_CARD_AND_FACE 身份证和人脸
 *
 * BANK_CARD3 银行卡号3要素
 *
 * BANK_CARD4 银行卡号4要素
 *
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@Component
public class RealNameChecker {

  @Autowired
  private ApplicationContext context;

  public RealNameVerify getVerify(String type) {
    if (Strings.isNullOrEmpty(type)) {
      return null;
    }
    return context.getBean(type.toLowerCase(), RealNameVerify.class);
  }

}
