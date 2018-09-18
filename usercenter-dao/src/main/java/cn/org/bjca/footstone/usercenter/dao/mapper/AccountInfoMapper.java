package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AccountInfoMapper {
    long countByExample(AccountInfoExample example);

    int deleteByExample(AccountInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    List<AccountInfo> selectByExampleWithRowbounds(AccountInfoExample example, RowBounds rowBounds);

    List<AccountInfo> selectByExample(AccountInfoExample example);

    AccountInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountInfo record,
        @Param("example") AccountInfoExample example);

    int updateByExample(@Param("record") AccountInfo record,
        @Param("example") AccountInfoExample example);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);

    Long sumByExample(AccountInfoExample example);

    void batchInsert(@Param("items") List<AccountInfo> items);
}