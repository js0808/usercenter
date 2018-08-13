package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.NotifyInfo;
import cn.org.bjca.footstone.usercenter.dao.model.NotifyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface NotifyInfoMapper {

  long countByExample(NotifyInfoExample example);

  int deleteByExample(NotifyInfoExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(NotifyInfo record);

  int insertSelective(NotifyInfo record);

  List<NotifyInfo> selectByExampleWithRowbounds(NotifyInfoExample example, RowBounds rowBounds);

  List<NotifyInfo> selectByExample(NotifyInfoExample example);

  NotifyInfo selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") NotifyInfo record,
      @Param("example") NotifyInfoExample example);

  int updateByExample(@Param("record") NotifyInfo record,
      @Param("example") NotifyInfoExample example);

  int updateByPrimaryKeySelective(NotifyInfo record);

  int updateByPrimaryKey(NotifyInfo record);

  Long sumByExample(NotifyInfoExample example);

  void batchInsert(@Param("items") List<NotifyInfo> items);
}