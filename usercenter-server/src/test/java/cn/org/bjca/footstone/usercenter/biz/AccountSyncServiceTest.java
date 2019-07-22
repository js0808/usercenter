package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EnterpriseSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountSyncResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntSyncResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 *
 * description:
 *
 * @author: wanghui<tinyhui.wang at gmail.com>
 * @date: 2019-07-19 17:27
 */

@Slf4j
public class AccountSyncServiceTest extends BaseTest {

  @Autowired
  private AccountSyncService accountSyncService;

  @Test
  public void testSyncAccount(){
    AccountSyncRequest request = new AccountSyncRequest();
    request.setEid(872);
    request.setAccountType("");
    request.setAccount("");
    request.setPassword("");
    request.setStatus("");
    request.setUserType("");
    request.setAppId("");

    AccountSyncResponse response = accountSyncService.syncAccount(request);
    System.out.println(response);
  }
  @Test
  public void test(){
    EnterpriseSyncRequest request = new EnterpriseSyncRequest();
    request.setHeadImgUrl("");
    request.setName("test");
    request.setPhone("");
    request.setOrgCode("");
    request.setBizLicense("");
    request.setSocialCreditCode("");
    request.setLegalName("");
    request.setLegalidNum("");
    request.setRealNameFlag(1);
    request.setRealNameType("");
    request.setReviewFlag(1);
    request.setBizLicenseImageUrl("");
    request.setOrgCodeImageUrl("");
    request.setLegalIdFrontImageUrl("");
    request.setLegalIdBackImageUrl("");
    request.setAccountName("");
    request.setBankAccount("");
    request.setBankName("");
    request.setBankAddressCode("");
    request.setStatus("");
    request.setOper("");
    request.setAppId("");

    EntSyncResponse entSyncResponse = accountSyncService.syncEnt(request);
    System.out.println(entSyncResponse);
  }

}
