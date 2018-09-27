package cn.org.bjca.footstone.usercenter.api.commons;

/**
 * @author LvYong
 * @create 2018-03-06
 **/
public interface Conts {

  int SC_OK = 200;

  int REALNAME_RESULT_SUCCESS = 200;

  String REALNAME_RESULT_SAME = "一致";

  /**
   * 0：null（当身份核验不一致时返回） 1：系统判断为同一人 2：系统判断为不同人 3：库中无照片
   */
  String REALNAME_FACE_RESULT_SUCCESS = "1";


}
