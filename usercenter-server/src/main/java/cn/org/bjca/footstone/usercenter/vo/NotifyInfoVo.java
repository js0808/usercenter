package cn.org.bjca.footstone.usercenter.vo;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/23
 **/
@Data
@Builder
public class NotifyInfoVo<T> {

  private String type;

  private T data;

  private Date timestamp;

}
