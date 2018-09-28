package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EmailCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeApplyResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeValidateResponse;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import cn.org.bjca.footstone.usercenter.vo.AuthorCodeReqVo;
import cn.org.bjca.footstone.usercenter.vo.CodeValidateReqVo;
import cn.org.bjca.footstone.usercenter.vo.MailCodeReqVo;
import cn.org.bjca.footstone.usercenter.config.AuthCodeConfig;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.SignatureUtils;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.client.RestTemplate;

import static cn.org.bjca.footstone.usercenter.util.RestUtils.isOk;
import static cn.org.bjca.footstone.usercenter.util.RestUtils.post;

@Service
@Slf4j
public class AuthCodeService {

  private final String redis_key = "usercenter_";
  private final String validate_key = "validate_";

  private final String rule_email = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

  @Autowired
  private AuthCodeConfig authCodeConfig;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private RestTemplate restTemplate;

  public AuthCodeApplyResponse codeApply(AuthCodeApplyRequest request) throws Exception {

    /**组装请求参数**/
    AuthorCodeReqVo send = new AuthorCodeReqVo();
    send.setVersion(authCodeConfig.getVersion());
    send.setAppId(authCodeConfig.getAppId());
    send.setMobile(request.getMobile());
    send.setParam("{}");
    send.setExtension("{}");
    send.setDeviceId(authCodeConfig.getDeviceId());
    send.setTemplateId(authCodeConfig.getTemplateId());
    send.setTransId(String.valueOf(System.currentTimeMillis()));
    send.setSignAlgo(authCodeConfig.getSignAlgo());

    ResponseEntity<ReturnResult<AuthCodeApplyResponse>> responseEntity = null;
    responseEntity = RestUtils.post(authCodeConfig.getCodeUrl(),
        new ParameterizedTypeReference<ReturnResult<AuthCodeApplyResponse>>() {
        }, send);
    log.info("codeApply post return:[{}]", JSONObject.toJSONString(responseEntity));
    if (isOk(responseEntity)) {
      ReturnResult<AuthCodeApplyResponse> returnResult = responseEntity.getBody();
      if (returnResult.getStatus() != 200) {
        throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR, returnResult.getMessage());
      }
      log.info("data:[{}]", JSONObject.toJSONString(returnResult.getData()));

      AuthCodeApplyResponse response = responseEntity.getBody().getData();
      return response;
    } else {
      log.error("status:[{}]", responseEntity.getStatusCode().value());
      log.error("message:[{}]", responseEntity.getBody().getMessage());
      throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR,
          responseEntity.getBody().getMessage());
    }
  }

  public void emailCodeApply(EmailCodeApplyRequest request) throws Exception {

    /**组装请求参数**/
    MailCodeReqVo send = new MailCodeReqVo();
    send.setSubject(request.getSubject());
    send.setTo(request.getEmail());
    send.setFromName(authCodeConfig.getFromName());
    AuthCodeTypeEnum typeEnum = AuthCodeTypeEnum.findByValue(request.getType());
    String emailCode = getFixLenthString(6);
    String sendMsg = String
        .format(authCodeConfig.getEmailBody(), request.getEmail(), emailCode, typeEnum.getDesc());
    send.setPlainText(sendMsg);

    HttpHeaders headers = new HttpHeaders();
    headers.add("develop-id", authCodeConfig.getDevelopId());
    headers.add("develop-key ", authCodeConfig.getDevelopKey());
    HttpEntity<MailCodeReqVo> entity = new HttpEntity<>(send, headers);

    ResponseEntity<ReturnResult> responseEntity = restTemplate
        .postForEntity(authCodeConfig.getEmailUrl(), entity, ReturnResult.class);
    log.info("send mail http result is {}, body is {}", responseEntity.getStatusCodeValue(),
        responseEntity.getBody());

    if (isOk(responseEntity)) {
      ReturnResult<AuthCodeApplyResponse> returnResult = responseEntity.getBody();
      if (returnResult.getStatus() != 200) {
        throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR, returnResult.getMessage());
      }
    } else {
      log.error("status:[{}]", responseEntity.getStatusCode().value());
      log.error("message:[{}]", responseEntity.getBody().getMessage());
      throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR,
          responseEntity.getBody().getMessage());
    }
    String key = redis_key + typeEnum.value() + "_" + request.getEmail();
    stringRedisTemplate.opsForValue()
        .set(key, emailCode, authCodeConfig.getExpire(), TimeUnit.SECONDS);
  }

  public AuthCodeValidateResponse validate(AuthCodeValidateRequest request) throws Exception {
    if (!isEmail(request.getUserName())) {
      CodeValidateReqVo send = new CodeValidateReqVo();
      send.setAppId(authCodeConfig.getAppId());
      send.setDeviceId(authCodeConfig.getDeviceId());
      send.setSignAlgo(authCodeConfig.getSignAlgo());
      send.setMobile(request.getUserName());
      send.setTransId(String.valueOf(System.currentTimeMillis()));
      send.setTemplateId(authCodeConfig.getTemplateId());
      send.setVersion(authCodeConfig.getVersion());
      send.setAuthCode(request.getAuthCode());

      ResponseEntity<ReturnResult> responseEntity = null;
      responseEntity = post(authCodeConfig.getValidateUrl(), false, ReturnResult.class, send);
      log.info("validate post return:[{}]", JSONObject.toJSONString(responseEntity));
      if (isOk(responseEntity)) {
        ReturnResult returnResult = responseEntity.getBody();
        if (returnResult.getStatus() != 200) {
          throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR, returnResult.getMessage());
        }
      } else {
        log.error("status:[{}]", responseEntity.getStatusCode().value());
        log.error("message:[{}]", responseEntity.getBody().getMessage());
        throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR,
            responseEntity.getBody().getMessage());
      }
    } else {
      AuthCodeTypeEnum typeEnum = AuthCodeTypeEnum.findByValue(request.getType());
      String key = redis_key + typeEnum.value() + "_" + request.getUserName();
      String redisValue = stringRedisTemplate.opsForValue().get(key);
      if (StringUtils.isEmpty(redisValue)) {
        throw new BjcaBizException(ReturnCodeEnum.AUTH_CODE_NOT_EXIT_ERROR);
      }
      if (!StringUtils.equals(redisValue, request.getAuthCode())) {
        throw new BjcaBizException(ReturnCodeEnum.AUTH_CODE_VALIDATE_ERROR);
      }
    }
    AuthCodeValidateResponse response = new AuthCodeValidateResponse();
    if (StringUtils.equals(AuthCodeTypeEnum.CHANGE.value(), request.getType())) {
      String key = validate_key + request.getType() + "_" + request.getUserName();
      String uuidValue = getUUID();
      stringRedisTemplate.opsForValue().set(key, uuidValue, 10, TimeUnit.MINUTES);
      response.setValidateId(uuidValue);
    }
    return response;
  }

  public boolean isEmail(String account) {
    Pattern p = Pattern.compile(rule_email);
    Matcher m = p.matcher(account);
    return m.matches();
  }

  public String getFixLenthString(int strLength) {
    Random rm = new Random();
    // 获得随机数
    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

    // 返回固定的长度的随机数
    return String.valueOf(pross).substring(1, strLength + 1);
  }

  public String getUUID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

  public String getRedis_key() {
    return redis_key;
  }

  public String getValidate_key() {
    return validate_key;
  }
}
