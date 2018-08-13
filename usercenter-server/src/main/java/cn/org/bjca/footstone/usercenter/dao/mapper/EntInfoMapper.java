package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface EntInfoMapper {
    long countByExample(EntInfoExample example);

    int deleteByExample(EntInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EntInfo record);

    int insertSelective(EntInfo record);

    List<EntInfo> selectByExampleWithBLOBsWithRowbounds(EntInfoExample example, RowBounds rowBounds);

    List<EntInfo> selectByExampleWithBLOBs(EntInfoExample example);

    List<EntInfo> selectByExampleWithRowbounds(EntInfoExample example, RowBounds rowBounds);

    List<EntInfo> selectByExample(EntInfoExample example);

    EntInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EntInfo record,
        @Param("example") EntInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") EntInfo record,
        @Param("example") EntInfoExample example);

    int updateByExample(@Param("record") EntInfo record, @Param("example") EntInfoExample example);

    int updateByPrimaryKeySelective(EntInfo record);

    int updateByPrimaryKeyWithBLOBs(EntInfo record);

    int updateByPrimaryKey(EntInfo record);

    Long sumByExample(EntInfoExample example);

    void batchInsert(@Param("items") List<EntInfo> items);
}