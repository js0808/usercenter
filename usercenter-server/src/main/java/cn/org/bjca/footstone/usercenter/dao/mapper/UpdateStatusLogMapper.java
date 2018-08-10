package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.UpdateStatusLog;
import cn.org.bjca.footstone.usercenter.dao.model.UpdateStatusLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UpdateStatusLogMapper {

  long countByExample(UpdateStatusLogExample example);

  int deleteByExample(UpdateStatusLogExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(UpdateStatusLog record);

  int insertSelective(UpdateStatusLog record);

  List<UpdateStatusLog> selectByExampleWithRowbounds(UpdateStatusLogExample example,
      RowBounds rowBounds);

  List<UpdateStatusLog> selectByExample(UpdateStatusLogExample example);

  UpdateStatusLog selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") UpdateStatusLog record,
      @Param("example") UpdateStatusLogExample example);

  int updateByExample(@Param("record") UpdateStatusLog record,
      @Param("example") UpdateStatusLogExample example);

  int updateByPrimaryKeySelective(UpdateStatusLog record);

  int updateByPrimaryKey(UpdateStatusLog record);

  Long sumByExample(UpdateStatusLogExample example);

  void batchInsert(@Param("items") List<UpdateStatusLog> items);
}