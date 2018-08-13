package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.UpdateInfoLog;
import cn.org.bjca.footstone.usercenter.dao.model.UpdateInfoLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UpdateInfoLogMapper {
    long countByExample(UpdateInfoLogExample example);

    int deleteByExample(UpdateInfoLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UpdateInfoLog record);

    int insertSelective(UpdateInfoLog record);

    List<UpdateInfoLog> selectByExampleWithRowbounds(UpdateInfoLogExample example,
        RowBounds rowBounds);

    List<UpdateInfoLog> selectByExample(UpdateInfoLogExample example);

    UpdateInfoLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UpdateInfoLog record,
        @Param("example") UpdateInfoLogExample example);

    int updateByExample(@Param("record") UpdateInfoLog record,
        @Param("example") UpdateInfoLogExample example);

    int updateByPrimaryKeySelective(UpdateInfoLog record);

    int updateByPrimaryKey(UpdateInfoLog record);

    Long sumByExample(UpdateInfoLogExample example);

    void batchInsert(@Param("items") List<UpdateInfoLog> items);
}