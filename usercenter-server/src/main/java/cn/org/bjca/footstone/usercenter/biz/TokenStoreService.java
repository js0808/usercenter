package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.vo.LoginTokenVo;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
public interface TokenStoreService {

  void store(LoginTokenVo loginTokenVo);

  boolean removeToken(Long uid, String token);

  LoginTokenVo readToken(Long uid, String token);
}
