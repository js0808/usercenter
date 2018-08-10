package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserInfoMapper {

  long countByExample(UserInfoExample example);

  int deleteByExample(UserInfoExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(UserInfo record);

  int insertSelective(UserInfo record);

  List<UserInfo> selectByExampleWithRowbounds(UserInfoExample example, RowBounds rowBounds);

  List<UserInfo> selectByExample(UserInfoExample example);

  UserInfo selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") UserInfo record,
      @Param("example") UserInfoExample example);

  int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

  int updateByPrimaryKeySelective(UserInfo record);

  int updateByPrimaryKey(UserInfo record);

  Long sumByExample(UserInfoExample example);

  void batchInsert(@Param("items") List<UserInfo> items);
}