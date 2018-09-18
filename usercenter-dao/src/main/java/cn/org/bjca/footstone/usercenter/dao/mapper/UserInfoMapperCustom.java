package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoAccountJoin;

public interface UserInfoMapperCustom {

  UserInfo selectByUid(Long uid);

  UserInfoAccountJoin selectByAccount(String account);
}