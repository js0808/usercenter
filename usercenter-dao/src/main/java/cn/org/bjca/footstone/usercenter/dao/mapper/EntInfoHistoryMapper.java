package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.EntInfoHistory;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface EntInfoHistoryMapper {

  long countByExample(EntInfoHistoryExample example);

  int deleteByExample(EntInfoHistoryExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(EntInfoHistory record);

  int insertSelective(EntInfoHistory record);

  List<EntInfoHistory> selectByExampleWithRowbounds(EntInfoHistoryExample example,
      RowBounds rowBounds);

  List<EntInfoHistory> selectByExample(EntInfoHistoryExample example);

  EntInfoHistory selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") EntInfoHistory record,
      @Param("example") EntInfoHistoryExample example);

  int updateByExample(@Param("record") EntInfoHistory record,
      @Param("example") EntInfoHistoryExample example);

  int updateByPrimaryKeySelective(EntInfoHistory record);

  int updateByPrimaryKey(EntInfoHistory record);

  Long sumByExample(EntInfoHistoryExample example);

  void batchInsert(@Param("items") List<EntInfoHistory> items);
}