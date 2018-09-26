package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.RealNameVerifyRequest;
import cn.org.bjca.footstone.usercenter.dao.model.RealNameVerifyRequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RealNameVerifyRequestMapper {

  long countByExample(RealNameVerifyRequestExample example);

  int deleteByExample(RealNameVerifyRequestExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(RealNameVerifyRequest record);

  int insertSelective(RealNameVerifyRequest record);

  List<RealNameVerifyRequest> selectByExampleWithRowbounds(RealNameVerifyRequestExample example,
      RowBounds rowBounds);

  List<RealNameVerifyRequest> selectByExample(RealNameVerifyRequestExample example);

  RealNameVerifyRequest selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") RealNameVerifyRequest record,
      @Param("example") RealNameVerifyRequestExample example);

  int updateByExample(@Param("record") RealNameVerifyRequest record,
      @Param("example") RealNameVerifyRequestExample example);

  int updateByPrimaryKeySelective(RealNameVerifyRequest record);

  int updateByPrimaryKey(RealNameVerifyRequest record);

  Long sumByExample(RealNameVerifyRequestExample example);

  void batchInsert(@Param("items") List<RealNameVerifyRequest> items);
}