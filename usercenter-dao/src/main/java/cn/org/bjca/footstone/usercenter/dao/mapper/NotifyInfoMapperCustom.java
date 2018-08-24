package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.NotifyInfo;
import java.util.List;

public interface NotifyInfoMapperCustom {

  List<NotifyInfo> selectInitNotify();
}