package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoVo;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/13
 * @since 1.0
 */
@Service
public class UserInfoService {

  private static final BeanCopier USERINFO_COPIER = BeanCopier
      .create(UserInfoVo.class, UserInfo.class, false);

  @Autowired
  private UserInfoMapper userInfoMapper;

  public void addUser(UserInfoVo userInfoVo) {
    UserInfo userInfo = createUserInfo();
    USERINFO_COPIER.copy(userInfoVo, userInfo, null);

    int i = userInfoMapper.insertSelective(userInfo);
  }

  private UserInfo createUserInfo() {
    return new UserInfo();
  }

}
