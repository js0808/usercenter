package cn.org.bjca.footstone.usercenter.dao.mapper;

import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface AccountInfoMapperCustom {

  List<AccountInfo> selectLockAccount(@Param("now") Date unlockTime);
}