package cn.org.bjca.footstone.usercenter.biz.realname;

import static cn.org.bjca.footstone.usercenter.api.commons.Conts.REALNAME_RESULT_SUCCESS;
import static cn.org.bjca.footstone.usercenter.api.commons.Conts.SC_OK;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.REALNAME_PERSON_ERROR;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.config.AuthCodeConfig;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import cn.org.bjca.footstone.usercenter.util.SignatureUtils;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import com.google.common.collect.Maps;
import java.text.MessageFormat;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/14
 * @since 1.0
 */
@Slf4j
public abstract class RealNameVerify {

  private UserInfoVo userInfoVo;

  protected String host;

  @Autowired
  private AuthCodeConfig config;

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
//    String signStr = SignatureUtils
//        .signatureBean(param, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, config.getSignKey());
//    param.put("signature", signStr);

    ResponseEntity<ReturnResult<Map<String, String>>> result = RestUtils
        .post(url, new ParameterizedTypeReference<ReturnResult<Map<String, String>>>() {
        }, param);
    log.info("调用实名认证接口{}, 参数{}, 返回结果{}", url, param, result.getBody());

    if (result.getBody().getStatus() != SC_OK) {
      throw new BaseException(REALNAME_PERSON_ERROR, MessageFormat
          .format("status : {0}, message : {1}", result.getBody().getStatus(),
              result.getBody().getMessage()));
    }

    Pair<Boolean, String> judge = judgeSuccess(result.getBody().getData());
    if (!judge.getKey()) {
      throw new BaseException(REALNAME_PERSON_ERROR, judge.getValue());
    }
  }

  protected Map<String, String> createParam() {
    Map<String, String> param = Maps.newHashMap();
    param.put("appId", config.getAppId());
    param.put("deviceId", config.getDeviceId());
    param.put("version", config.getVersion());
    param.put("signAlgo", SignatureUtils.SIGN_ALGORITHMS_HMACSHA256);
    param.put("userTransId", String.valueOf(SnowFlake.next()));
    return param;
  }

  protected abstract String getUrl();

  protected Pair<Boolean, String> judgeSuccess(Map<String, String> result) {
    if (Integer.parseInt(result.get("resultCode")) == REALNAME_RESULT_SUCCESS) {
      return Pair.of(true, "");
    }
    String resultMessage = result.get("resultMessage");
    return Pair.of(false, MessageFormat
        .format("message : {0}", resultMessage == null ? "" : resultMessage));
  }

}
