package cn.org.bjca.footstone.usercenter.exceptions;

import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import com.baomidou.mybatisplus.toolkit.ArrayUtils;

/**
 * @author LvYong
 * @create 2017-08-23
 **/
public class BaseException extends RuntimeException {

    private ReturnCodeEnum returnCodeEnum;

    private String message;

    public BaseException(ReturnCodeEnum returnStatusEnum, Object... params) {
        this.returnCodeEnum = returnStatusEnum;
        if (ArrayUtils.isNotEmpty(params)) {
            this.message = String.format(returnStatusEnum.getDesc(), params);
        } else {
            this.message = returnStatusEnum.getDesc();
        }
    }

    public BaseException(ReturnCodeEnum returnStatusEnum, Throwable cause) {
        super(returnStatusEnum.getDesc(), cause);
        this.returnCodeEnum = returnStatusEnum;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable t) {
        super(message, t);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getResultCode() {
        return returnCodeEnum.value();
    }

    public ReturnCodeEnum getReturnStatusEnum() {
        return returnCodeEnum;
    }

    /**
     * ignore stacktrace
     *
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
