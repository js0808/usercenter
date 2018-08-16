package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample.Criteria;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Slf4j
@Service
public class AccountInfoService {

  @Autowired
  private AccountInfoMapper accountInfoMapper;

  public AccountInfo findAccountInfoByAccount(String account) {
    AccountInfoExample example = new AccountInfoExample();
    Criteria criteria = example.createCriteria();
    criteria.andAccountEqualTo(account);
    List<AccountInfo> accountInfos = accountInfoMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(accountInfos)) {
      return null;
    }
    return accountInfos.get(0);
  }

  public AccountInfo findAccountInfoByUid(Long uid) {
    AccountInfoExample example = new AccountInfoExample();
    Criteria criteria = example.createCriteria();
    criteria.andUidEqualTo(uid);
    List<AccountInfo> accountInfos = accountInfoMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(accountInfos)) {
      return null;
    }
    return accountInfos.get(0);
  }
}
