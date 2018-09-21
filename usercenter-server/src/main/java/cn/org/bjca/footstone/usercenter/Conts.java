package cn.org.bjca.footstone.usercenter;

import cn.org.bjca.footstone.usercenter.api.vo.response.AccountInfoResponse;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
public interface Conts {

  BeanCopier ACCOUNT_INFO_TO_RESPONSE = BeanCopier
      .create(AccountInfo.class, AccountInfoResponse.class, false);

  String CAPTCHA_REDIS_PREFIX = "CAPTCHA_";

  int CAPTCHA_EXPIRE_MIN = 5;

  String SAVED = "SAVED";

}
