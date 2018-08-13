package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.UserInfoExt;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserInfoExtMapper {

  long countByExample(UserInfoExtExample example);

  int deleteByExample(UserInfoExtExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(UserInfoExt record);

  int insertSelective(UserInfoExt record);

  List<UserInfoExt> selectByExampleWithRowbounds(UserInfoExtExample example, RowBounds rowBounds);

  List<UserInfoExt> selectByExample(UserInfoExtExample example);

  UserInfoExt selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") UserInfoExt record,
      @Param("example") UserInfoExtExample example);

  int updateByExample(@Param("record") UserInfoExt record,
      @Param("example") UserInfoExtExample example);

  int updateByPrimaryKeySelective(UserInfoExt record);

  int updateByPrimaryKey(UserInfoExt record);

  Long sumByExample(UserInfoExtExample example);

  void batchInsert(@Param("items") List<UserInfoExt> items);
}