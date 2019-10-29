package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.EntPayVerifyRequest;
import cn.org.bjca.footstone.usercenter.dao.model.EntPayVerifyRequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface EntPayVerifyRequestMapper {
    long countByExample(EntPayVerifyRequestExample example);

    int deleteByExample(EntPayVerifyRequestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EntPayVerifyRequest record);

    int insertSelective(EntPayVerifyRequest record);

    List<EntPayVerifyRequest> selectByExampleWithRowbounds(EntPayVerifyRequestExample example, RowBounds rowBounds);

    List<EntPayVerifyRequest> selectByExample(EntPayVerifyRequestExample example);

    EntPayVerifyRequest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EntPayVerifyRequest record, @Param("example") EntPayVerifyRequestExample example);

    int updateByExample(@Param("record") EntPayVerifyRequest record, @Param("example") EntPayVerifyRequestExample example);

    int updateByPrimaryKeySelective(EntPayVerifyRequest record);

    int updateByPrimaryKey(EntPayVerifyRequest record);

    Long sumByExample(EntPayVerifyRequestExample example);

    void batchInsert(@Param("items") List<EntPayVerifyRequest> items);
}