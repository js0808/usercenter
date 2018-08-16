package cn.org.bjca.footstone.usercenter.vo;

import lombok.Data;

/**
 * @Description:
 * @Author:baoqb
 * @Date:18/8/14
 */
@Data
public class BaseParam {

  public String version;
  public String signAlgo;
  public String signature;
  public String deviceId;
  public String appId;
}
