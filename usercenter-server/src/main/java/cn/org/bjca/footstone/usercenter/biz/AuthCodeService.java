package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeApplyRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeApplyResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.AuthCodeValidateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthCodeService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Value("#{'${msg.sendmsg-url}'}")
    private String sendMsgUrl = null;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public AuthCodeApplyResponse codeApply(AuthCodeApplyRequest request) {

        AuthCodeApplyResponse response = new AuthCodeApplyResponse();
        return response;
    }

    public AuthCodeValidateResponse validate(AuthCodeValidateRequest request) {
        AuthCodeValidateResponse response = new AuthCodeValidateResponse();
        return response;
    }
}
