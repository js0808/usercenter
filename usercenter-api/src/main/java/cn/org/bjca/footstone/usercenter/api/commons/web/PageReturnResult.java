package cn.org.bjca.footstone.usercenter.api.commons.web;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LvYong
 * @create 2017-12-07
 **/
@Data
@NoArgsConstructor
@ApiModel("分页返回结果")
public class PageReturnResult<T> extends ReturnResult<T> implements Serializable {
    private static final long serialVersionUID = -124702359743467410L;
    private Meta meta;

    public PageReturnResult(int status) {
        super(status);
    }

    public PageReturnResult(int status, String message) {
        super(status, message);
    }

    public PageReturnResult(int status, String message, T data) {
        super(status, message, data);
    }

    public PageReturnResult(int status, String message, T data, Meta meta) {
        super(status, message, data);
        this.meta = meta;
    }
}
