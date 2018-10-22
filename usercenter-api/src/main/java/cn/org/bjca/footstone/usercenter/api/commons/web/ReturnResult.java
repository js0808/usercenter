package cn.org.bjca.footstone.usercenter.api.commons.web;

import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static cn.org.bjca.footstone.usercenter.api.commons.Conts.SC_OK;

/**
 * @author LvYong
 * @create 2017-11-29 <p> web返回结果定义
 **/
@ToString
@Getter
@Setter
@NoArgsConstructor
@ApiModel("返回结果")
public class ReturnResult<T> implements Serializable {

  private static final long serialVersionUID = 6948768594132295784L;
  @ApiModelProperty("状态码")
  private int status;
  @ApiModelProperty("提示信息")
  private String message;
  @ApiModelProperty("数据")
  private T data;

  public ReturnResult(int status) {
    this(status, null);
  }

  public ReturnResult(int status, String message) {
    this(status, message, null);
  }

  public ReturnResult(int status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  /**
   * 构建一个成功结果的对象
   */
  public static ReturnResult success(String message) {
    return build(SC_OK, message, null);
  }

  /**
   * 构建一个成功结果的对象
   */
  public static <T> ReturnResult success(String message, T data) {
    return build(SC_OK, message, data);
  }

  /**
   * 构建一个成功结果的对象
   */
  public static <T> ReturnResult success(T data) {
    return build(SC_OK, "SUCCESS", data);
  }


  public static ReturnResult error(ReturnCodeEnum returnCodeEnum) {
    return build(returnCodeEnum.value(), returnCodeEnum.getDesc(), null);
  }

  public static <T> ReturnResult error(ReturnCodeEnum returnCodeEnum, T data) {
    return build(returnCodeEnum.value(), returnCodeEnum.getDesc(), data);
  }

  /**
   * 构建一个结果的对象
   */
  public static <T> ReturnResult build(int code, String message, T data) {
    ReturnResult vo = new ReturnResult(code);
    vo.setMessage(message);
    vo.setData(data);
    return vo;
  }
}
