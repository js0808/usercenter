package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EmailCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeApplyResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeValidateResponse;
import cn.org.bjca.footstone.usercenter.biz.vo.request.AuthorCodeSend;
import cn.org.bjca.footstone.usercenter.biz.vo.request.MailCodeSend;
import cn.org.bjca.footstone.usercenter.config.AuthCodeConfig;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.SignatureUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static cn.org.bjca.footstone.usercenter.util.RestUtils.isOk;
import static cn.org.bjca.footstone.usercenter.util.RestUtils.post;

@Service
@Slf4j
public class AuthCodeService {

    private final String redis_key = "usercenter_";
    private String message = "亲爱的%s用户您好, \n验证码：%s，5分钟内输入有效，请输入验证码完成%s，勿将验证码告诉其他人。";
    @Autowired
    private AuthCodeConfig authCodeConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public AuthCodeApplyResponse codeApply(AuthCodeApplyRequest request) throws Exception {

        /**组装请求参数**/
        AuthorCodeSend send = new AuthorCodeSend();
        send.setVersion(authCodeConfig.getVersion());
        send.setAppId(authCodeConfig.getAppId());
        send.setMobile(request.getMobile());
        send.setParam("{}");
        send.setExtension("{}");
        send.setDeviceId(InetAddress.getLocalHost().getHostAddress());
        send.setTemplateId(authCodeConfig.getTemplateId());
        send.setTransId(String.valueOf(System.currentTimeMillis()));
        send.setSignAlgo(authCodeConfig.getSignAlgo());
        String signStr = SignatureUtils.signatureBean(request, SignatureUtils.SIGN_ALGORITHMS_HMAC, authCodeConfig.getSignKey());
        send.setSignature(signStr);
        ResponseEntity<ReturnResult> responseEntity = null;
        responseEntity = post(authCodeConfig.getCodeUrl(), false, ReturnResult.class, send);
        log.info("codeApply post return:[{}]", JSONObject.toJSONString(responseEntity));
        if (isOk(responseEntity)) {
            ReturnResult<AuthCodeApplyResponse> returnResult = responseEntity.getBody();
            if (returnResult.getStatus() != 200) {
                throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR, returnResult.getMessage());
            }
            AuthCodeApplyResponse response = returnResult.getData();
            return response;
        } else {
            log.error("status:[{}]", responseEntity.getStatusCode().value());
            log.error("message:[{}]", responseEntity.getBody().getMessage());
            throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR, responseEntity.getBody().getMessage());
        }
    }

    public void emailCodeApply(EmailCodeApplyRequest request) throws Exception {

        /**组装请求参数**/
        MailCodeSend send = new MailCodeSend();
        send.setSubject(request.getSubject());
        send.setTo(request.getEmail());
        send.setFromName(authCodeConfig.getFromName());
        AuthCodeTypeEnum typeEnum = AuthCodeTypeEnum.findByValue(request.getType());
        String emailCode = getFixLenthString(6);
        String sendMsg = String.format(message, request.getEmail(), emailCode, typeEnum.getDesc());
        send.setPlainText(sendMsg);
        ResponseEntity<ReturnResult> responseEntity = null;
        responseEntity = post(authCodeConfig.getEmailUrl(), false, ReturnResult.class, send);
        log.info("emailCodeApply post return:[{}]", JSONObject.toJSONString(responseEntity));
        if (isOk(responseEntity)) {
            ReturnResult<AuthCodeApplyResponse> returnResult = responseEntity.getBody();
            if (returnResult.getStatus() != 200) {
                throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR, returnResult.getMessage());
            }
        } else {
            log.error("status:[{}]", responseEntity.getStatusCode().value());
            log.error("message:[{}]", responseEntity.getBody().getMessage());
            throw new BjcaBizException(ReturnCodeEnum.MSG_SERVER_ERROR, responseEntity.getBody().getMessage());
        }
        String key = redis_key + typeEnum.value() + "_" + request.getEmail();
        stringRedisTemplate.opsForValue().set(key, emailCode, authCodeConfig.getExpire(), TimeUnit.SECONDS);
    }

    public AuthCodeValidateResponse validate(AuthCodeValidateRequest request) {
        AuthCodeValidateResponse response = new AuthCodeValidateResponse();
        return response;
    }

    public String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 返回固定的长度的随机数
        return String.valueOf(pross).substring(1, strLength + 1);
    }
}
