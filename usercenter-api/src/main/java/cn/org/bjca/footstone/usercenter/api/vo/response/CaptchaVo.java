package cn.org.bjca.footstone.usercenter.api.vo.response;

import lombok.Data;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/27
 * @since 1.0
 */
@Data
public class CaptchaVo {

  private String ticket;
  private String image;

  public CaptchaVo() {

  }

  public CaptchaVo(String ticket, String image) {
    this.ticket = ticket;
    this.image = image;
  }
}
