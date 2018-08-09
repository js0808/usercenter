package cn.org.bjca.footstone.usercenter.web.handler;

import cn.org.bjca.footstone.usercenter.api.commons.web.ReturnResult;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author LvYong
 * @create 2017-08-23
 **/
@Slf4j
@ControllerAdvice
public class GloableExceptionHandler {

    public static final String ERR_CODE = "errCode";
    public static final String ERR_MSG  = "errMsg";

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ReturnResult handleException(Exception ex, WebRequest request) {
        ReturnCodeEnum returnCodeEnum = ReturnCodeEnum.ERROR;
        if (ex instanceof BaseException) {
            BaseException  e        = (BaseException) ex;
            ReturnCodeEnum codeEnum = e.getReturnStatusEnum();
            if (codeEnum != null) {
                returnCodeEnum = codeEnum;
            }
        } else if (ex instanceof BjcaBizException) {
            return getResponseEntityOfBizEx((BjcaBizException) ex, request);
        } else if (ex instanceof DataAccessException || ex instanceof SQLException) {
            log.error("返回异常 {} {}", ReturnCodeEnum.SQL_EXCEPTION, ex);
            log.error("异常信息:", ex);
            return new ReturnResult(ReturnCodeEnum.SQL_EXCEPTION.value(), ReturnCodeEnum.SQL_EXCEPTION.getDesc());
        }
        return getResponseEntity(ex, request, returnCodeEnum);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ReturnResult processValidationError(HttpServletResponse response, BindException ex) {
        BindingResult       result      = ex.getBindingResult();
        Map<String, String> fieldErrors = processFieldErrors(result.getFieldErrors());

        ReturnResult resultVo = new ReturnResult(ReturnCodeEnum.REQ_PARAM_ERR.value());
        resultVo.setMessage(ReturnCodeEnum.REQ_PARAM_ERR.getDesc());
        resultVo.setData(ImmutableMap.of(ERR_CODE, ReturnCodeEnum.REQ_PARAM_ERR.value(), ERR_MSG,
                                         JSON.toJSONString(fieldErrors)));
        return resultVo;
    }

    private Map<String, String> processFieldErrors(List<FieldError> fieldErrors) {
        Map<String, String> fieldErrorsMap = Maps.newHashMap();
        fieldErrorsMap.put(fieldErrors.get(0).getField(), fieldErrors.get(0).getDefaultMessage());
        return fieldErrorsMap;
    }

    /**
     * 根据各种异常构建 ResponseEntity 实体. 服务于以上各种异常
     *
     * @param ex
     * @param request
     * @param statusEnum
     * @return
     */
    private ReturnResult getResponseEntity(Exception ex, WebRequest request, ReturnCodeEnum statusEnum) {
        log.error("返回异常 {} {}", statusEnum, ex);
        log.error("异常信息:", ex);
        String       message  = ex.getMessage();
        ReturnResult resultVo = new ReturnResult(statusEnum.value());
        if (StringUtils.isNotEmpty(message)) {
            resultVo.setMessage(message);
        } else {
            resultVo.setMessage(statusEnum.getDesc());
        }
        return resultVo;
    }

    /**
     * 根据各种异常构建 ResponseEntity 实体. 服务于以上各种异常
     *
     * @param ex
     * @param request
     * @return
     */
    private ReturnResult getResponseEntityOfBizEx(BjcaBizException ex, WebRequest request) {
        ReturnCodeEnum statusEnum = ex.getReturnStatusEnum();
        log.error("返回异常 {} {}", statusEnum, ex);
        log.error("异常信息:", ex);
        String       message  = ex.getMessage();
        ReturnResult resultVo = new ReturnResult(statusEnum.value());
        if (StringUtils.isNotEmpty(message)) {
            resultVo.setMessage(message);
        } else {
            resultVo.setMessage(statusEnum.getDesc());
        }
        return resultVo;
    }
}
