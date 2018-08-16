package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.EntInfoExt;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface EntInfoExtMapper {
    long countByExample(EntInfoExtExample example);

    int deleteByExample(EntInfoExtExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EntInfoExt record);

    int insertSelective(EntInfoExt record);

    List<EntInfoExt> selectByExampleWithRowbounds(EntInfoExtExample example, RowBounds rowBounds);

    List<EntInfoExt> selectByExample(EntInfoExtExample example);

    EntInfoExt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EntInfoExt record, @Param("example") EntInfoExtExample example);

    int updateByExample(@Param("record") EntInfoExt record, @Param("example") EntInfoExtExample example);

    int updateByPrimaryKeySelective(EntInfoExt record);

    int updateByPrimaryKey(EntInfoExt record);

    Long sumByExample(EntInfoExtExample example);

    void batchInsert(@Param("items") List<EntInfoExt> items);
}