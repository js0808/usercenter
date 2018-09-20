package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.UserInfoHistory;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserInfoHistoryMapper {
    long countByExample(UserInfoHistoryExample example);

    int deleteByExample(UserInfoHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoHistory record);

    int insertSelective(UserInfoHistory record);

    List<UserInfoHistory> selectByExampleWithRowbounds(UserInfoHistoryExample example,
        RowBounds rowBounds);

    List<UserInfoHistory> selectByExample(UserInfoHistoryExample example);

    UserInfoHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfoHistory record,
        @Param("example") UserInfoHistoryExample example);

    int updateByExample(@Param("record") UserInfoHistory record,
        @Param("example") UserInfoHistoryExample example);

    int updateByPrimaryKeySelective(UserInfoHistory record);

    int updateByPrimaryKey(UserInfoHistory record);

    Long sumByExample(UserInfoHistoryExample example);

    void batchInsert(@Param("items") List<UserInfoHistory> items);
}