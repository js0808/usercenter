package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeApplyResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeValidateResponse;
import cn.org.bjca.footstone.usercenter.biz.vo.request.AuthorCodeSend;
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

import static cn.org.bjca.footstone.usercenter.util.RestUtils.isOk;
import static cn.org.bjca.footstone.usercenter.util.RestUtils.post;

@Service
@Slf4j
public class AuthCodeService {

    private final String redis_key = "usercenter_";
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

    public AuthCodeValidateResponse validate(AuthCodeValidateRequest request) {
        AuthCodeValidateResponse response = new AuthCodeValidateResponse();
        return response;
    }
}
