package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.EntInfoVerifyRequest;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoVerifyRequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface EntInfoVerifyRequestMapper {

  long countByExample(EntInfoVerifyRequestExample example);

  int deleteByExample(EntInfoVerifyRequestExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(EntInfoVerifyRequest record);

  int insertSelective(EntInfoVerifyRequest record);

  List<EntInfoVerifyRequest> selectByExampleWithRowbounds(EntInfoVerifyRequestExample example,
      RowBounds rowBounds);

  List<EntInfoVerifyRequest> selectByExample(EntInfoVerifyRequestExample example);

  EntInfoVerifyRequest selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") EntInfoVerifyRequest record,
      @Param("example") EntInfoVerifyRequestExample example);

  int updateByExample(@Param("record") EntInfoVerifyRequest record,
      @Param("example") EntInfoVerifyRequestExample example);

  int updateByPrimaryKeySelective(EntInfoVerifyRequest record);

  int updateByPrimaryKey(EntInfoVerifyRequest record);

  Long sumByExample(EntInfoVerifyRequestExample example);

  void batchInsert(@Param("items") List<EntInfoVerifyRequest> items);
}