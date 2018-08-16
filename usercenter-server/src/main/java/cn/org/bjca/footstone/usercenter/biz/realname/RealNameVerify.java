package cn.org.bjca.footstone.usercenter.biz.realname;

import static cn.org.bjca.footstone.usercenter.api.commons.Conts.REALNAME_RESULT_SUCCESS;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_PERSON_ERROR;

import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import com.google.common.collect.Maps;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@Slf4j
public abstract class RealNameVerify {

  private UserInfoVo userInfoVo;

  @Value("${usercenter.realname-host}")
  protected String host;
  @Value("${usercenter.username}")
  private String username;
  @Value("${usercenter.password}")
  private String password;

  public void setUserInfoVo(UserInfoVo userInfoVo) {
    this.userInfoVo = userInfoVo;
  }

  public UserInfoVo getUserInfoVo() {
    return userInfoVo;
  }

  public abstract Pair<Boolean, String> checkRequest();

  public void verify() {
    String url = getUrl();
    Map<String, String> param = createParam();
    ResponseEntity<Map<String, String>> result = RestUtils
        .post(url, new ParameterizedTypeReference<Map<String, String>>() {
        }, param);
    log.info("调用实名认证接口{}, 参数{}, 返回结果{}", url, param, result.getBody());

    Pair<Boolean, String> judge = judgeSuccess(result.getBody());
    if (!judge.getKey()) {
      throw new BaseException(REALNAME_PERSON_ERROR, judge.getValue());
    }
  }

  protected Map<String, String> createParam() {
    Map<String, String> param = Maps.newHashMap();
    param.put("userName", username);
    param.put("password", password);
    param.put("transactionId", String.valueOf(SnowFlake.next()));
    return param;
  }

  protected abstract String getUrl();

  protected Pair<Boolean, String> judgeSuccess(Map<String, String> result) {
    if (Objects.equals(result.get("resultCode"), REALNAME_RESULT_SUCCESS)) {
      return Pair.of(true, "");
    }
    return Pair.of(false, MessageFormat
        .format("code : {0}, message : {1}", result.get("resultCode"),
            result.get("resultMessage")));
  }

}
