package cn.org.bjca.footstone.usercenter.exceptions;

import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import com.baomidou.mybatisplus.toolkit.ArrayUtils;

/**
 * @author LvYong
 * @create 2017-12-07
 **/
public class BjcaBizException extends RuntimeException {

    private ReturnCodeEnum returnCodeEnum;

    private String message;

    public BjcaBizException(ReturnCodeEnum returnStatusEnum, Object... params) {
        this.returnCodeEnum = returnStatusEnum;
        if (ArrayUtils.isNotEmpty(params)) {
            this.message = String.format(returnStatusEnum.getDesc(), params);
        } else {
            this.message = returnStatusEnum.getDesc();
        }
    }

    public BjcaBizException(ReturnCodeEnum returnStatusEnum, Throwable cause) {
        super(returnStatusEnum.getDesc(), cause);
        this.returnCodeEnum = returnStatusEnum;
    }

    public BjcaBizException(String message) {
        super(message);
    }

    public BjcaBizException(String message, Throwable t) {
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
