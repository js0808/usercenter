package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.AccountAttempts;
import cn.org.bjca.footstone.usercenter.dao.model.AccountAttemptsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AccountAttemptsMapper {
    long countByExample(AccountAttemptsExample example);

    int deleteByExample(AccountAttemptsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountAttempts record);

    int insertSelective(AccountAttempts record);

    List<AccountAttempts> selectByExampleWithRowbounds(AccountAttemptsExample example, RowBounds rowBounds);

    List<AccountAttempts> selectByExample(AccountAttemptsExample example);

    AccountAttempts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountAttempts record, @Param("example") AccountAttemptsExample example);

    int updateByExample(@Param("record") AccountAttempts record, @Param("example") AccountAttemptsExample example);

    int updateByPrimaryKeySelective(AccountAttempts record);

    int updateByPrimaryKey(AccountAttempts record);

    Long sumByExample(AccountAttemptsExample example);

    void batchInsert(@Param("items") List<AccountAttempts> items);
}