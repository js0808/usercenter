package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.NotifyInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotifyInfoMapperCustom {

  int selectMinId();

  List<NotifyInfo> selectInitNotify(@Param("id") int id, @Param("selectLimit") int selectLimit);
}