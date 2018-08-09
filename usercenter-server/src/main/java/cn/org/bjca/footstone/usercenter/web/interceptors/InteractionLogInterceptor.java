package cn.org.bjca.footstone.usercenter.web.interceptors;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lvyong6
 */
@Aspect
@Component
@Slf4j
public class InteractionLogInterceptor {

    /**
     * 只有日志在INFO许可级别, 才是用拦截器输出 输出全格式如下: [{timestamp}]方法:*** [{timestamp}]入参:*** [{timestamp}]入参:***
     * [{timestamp}]结果:*** [{timestamp}]方法:***,耗时:{cost}毫秒 [{timestamp}]异常:*****
     */
    @Around(value = "execution(* cn.org.bjca.footstone.usercenter.web.controller.*.*(..))")
    public Object logAOP(final ProceedingJoinPoint joinPoint) throws Throwable {
        // 方法执行
        Object        rvt                 = null;
        Exception     exception           = null;
        long          startTime           = System.currentTimeMillis();
        String        resultStr           = "{}";
        StringBuilder requestParamBuilder = null;
        try {
            if (!log.isInfoEnabled()) {
                return joinPoint.proceed(joinPoint.getArgs());
            }
            log.info("{}[Begin]:{}", joinPoint.getSignature().toShortString(), startTime);
            // 参数为HttpServletRequest会重复调用getInputStream导致异常
            requestParamBuilder = buildRequest(joinPoint);

            try {
                rvt = joinPoint.proceed(joinPoint.getArgs());

                resultStr = JSON.toJSONString(rvt);
                // 将结果输出到日志, 超出500字符进行截取
                //  logger.info("[{}]结果:{}", startTime, StringUtils.substring(resultStr, 0, 500));
                // 将耗时超过1秒的Restful API输出到日志
                long endTime = System.currentTimeMillis();
                long msCost  = endTime - startTime;
                if (msCost > 1000) {
                    log.info("[{}]方法:{},耗时:{}毫秒", startTime, joinPoint.getSignature().toShortString(), msCost);
                }
            } catch (Exception e) {
                exception = e;
                throw e;
            }
            return rvt;
        } finally {
            MonitorLogger.log(joinPoint.getSignature().toShortString(), requestParamBuilder, resultStr,
                                                                      startTime, System.currentTimeMillis() - startTime, exception);

        }
    }

    private StringBuilder buildRequest(final ProceedingJoinPoint joinPoint) {
        // 参数为HttpServletRequest会重复调用getInputStream导致异常
        String        methodInfo          = joinPoint.getSignature().toShortString();
        StringBuilder requestParamBuilder = new StringBuilder(methodInfo);
        requestParamBuilder.append("=[");
        int i = 0;
        for (Object o : joinPoint.getArgs()) {
            if (!(o instanceof HttpServletResponse || o instanceof HttpServletRequest)) {
                String argValue = JSON.toJSONString(o);
                if (i != 0) {
                    requestParamBuilder.append(",");
                }
                requestParamBuilder.append(argValue);
                i++;
            }
        }
        requestParamBuilder.append("]");
        return requestParamBuilder;
    }

}
