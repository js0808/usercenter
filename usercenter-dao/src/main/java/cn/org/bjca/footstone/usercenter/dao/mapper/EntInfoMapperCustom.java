package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoAccountJoin;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/18
 * @since 1.0
 */
public interface EntInfoMapperCustom {

  EntInfo selectByUid(Long uid);

  EntInfoAccountJoin selectByAccount(String account);
}
