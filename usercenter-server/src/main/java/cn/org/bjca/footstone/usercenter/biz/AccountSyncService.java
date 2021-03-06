package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.enmus.AccountStatusEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.AccountTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.RealNameTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EnterpriseSyncRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.AccountSyncResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntSyncResponse;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoExample;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.PwdUtil;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * description:
 *
 * @author: wanghui<tinyhui.wang at gmail.com>
 * @date: 2019-07-19 16:19
 */

@Slf4j
@Service
public class AccountSyncService {

  @Autowired
  private EntInfoMapper entInfoMapper;

  @Autowired
  private AccountInfoService accountInfoService;

  @Autowired
  private AccountInfoMapper accountInfoMapper;

  public AccountSyncResponse syncAccount(AccountSyncRequest request) {
    if (request.getEid() == null || request.getEid() < 1) {
      log.warn("sync account ,eid {} is ", request.getEid());
      throw new BjcaBizException(ReturnCodeEnum.REQ_PARAM_ERR);

    }
    AccountSyncResponse response = new AccountSyncResponse();
    EntInfo entInfo = entInfoMapper.selectByPrimaryKey(request.getEid());
    if (entInfo == null) {
      log.warn("sync account ,eid {} is not exist", request.getEid());
      throw new BjcaBizException(ReturnCodeEnum.REQ_PARAM_ERR);
    }
    AccountInfo accountInfoByAccount = accountInfoService
        .findAccountInfoByAccount(request.getAccount());

    if (accountInfoByAccount != null) {
      if (!request.getAppId().equals(accountInfoByAccount.getAppId())) {
        response.setUid(accountInfoByAccount.getUid());
        return response;
      }
    }
    AccountInfo record = new AccountInfo();
    if (accountInfoByAccount != null) {
      record.setUid(accountInfoByAccount.getUid());
      record.setVersion(accountInfoByAccount.getVersion() + 1);
    } else {
      record.setUid(SnowFlake.next());
      record.setVersion(1);
    }
    AccountTypeEnum accountTypeEnum = AccountTypeEnum.findByValue(request.getAccountType());
    if (accountTypeEnum == null) {
      throw new BjcaBizException(ReturnCodeEnum.REQ_PARAM_ERR);
    }
    record.setAccountType(request.getAccountType());
    record.setAccount(request.getAccount());
    String password = PwdUtil.cipher(request.getPassword());
    record.setPassword(password);

    AccountStatusEnum accountStatusEnum = AccountStatusEnum.findByValue(request.getStatus());
    if (accountStatusEnum == null) {
      throw new BjcaBizException(ReturnCodeEnum.REQ_PARAM_ERR);
    }
    record.setStatus(request.getStatus());
    record.setRealnameId(request.getEid());
    UserTypeEnum userTypeEnum = UserTypeEnum.findByName(request.getUserType());
    if (userTypeEnum == null) {
      throw new BjcaBizException(ReturnCodeEnum.REQ_PARAM_ERR);
    }
    record.setUserType(request.getUserType());
    record.setIsLocked(false);
    record.setAppId(request.getAppId());
    if (accountInfoByAccount == null) {
      accountInfoMapper.insertSelective(record);
    } else {
      record.setId(accountInfoByAccount.getId());
      accountInfoMapper.updateByPrimaryKeySelective(record);
    }
    response.setUid(record.getUid());
    return response;
  }

  public EntSyncResponse syncEnt(EnterpriseSyncRequest request) {
    EntSyncResponse response = null;
    EntInfoExample entInfoExample = new EntInfoExample();

    entInfoExample.createCriteria().andNameEqualTo(request.getName())
        /**
         *  通过同步接口过来的企业默认是已实名状态，但是有通过其他方式录入进来的企业信息（名称相同），还在走审核流程，
         *  所以实名状态为未实名，所以如果有的话，需要返回已实名的企业信息。
         */
        .andRealNameFlagEqualTo(1);
    List<EntInfo> entInfoList = entInfoMapper.selectByExample(entInfoExample);
    log.info("entInfoList size = {}", entInfoList == null ? 0 : entInfoList.size());
    boolean ifExists = false;
    if (!CollectionUtils.isEmpty(entInfoList)) {
      ifExists = true;
      if (!request.getAppId()
          .equals(entInfoList.get(0).getAppId())) {//如果库里已经有这个企业名称了，但不是同一个appId，则不允许修改，直接返回
        response = new EntSyncResponse();
        response.setEid(entInfoList.get(0).getId());
        return response;
      }
    }
    log.info("ifExists = {}", ifExists);
    EntInfo entInfo = new EntInfo();
    entInfo.setHeadImgUrl(request.getHeadImgUrl());
    entInfo.setName(request.getName());
    entInfo.setPhone(request.getPhone());
    entInfo.setOrgCode(request.getOrgCode());
    entInfo.setBizLicense(request.getBizLicense());
    entInfo.setSocialCreditCode(request.getSocialCreditCode());
    entInfo.setLegalName(request.getLegalName());
    entInfo.setLegalIdNum(request.getLegalidNum());
    entInfo.setRealNameFlag(1);
    entInfo.setRealNameType(RealNameTypeEnum.OTHER.value());
    entInfo.setReviewFlag(request.getReviewFlag());
    entInfo.setBizLicenseImageUrl(request.getBizLicenseImageUrl());
    entInfo.setOrgCodeImageUrl(request.getOrgCodeImageUrl());
    entInfo.setLegalIdFrontImageUrl(request.getLegalIdFrontImageUrl());
    entInfo.setLegalIdBackImageUrl(request.getLegalIdBackImageUrl());
    entInfo.setAccountName(request.getAccountName());
    entInfo.setBankAccount(request.getBankAccount());
    entInfo.setBankName(request.getBankName());
    entInfo.setBankAddressCode(request.getBankAddressCode());
    entInfo.setAppId(request.getAppId());
    AccountStatusEnum accountStatusEnum = AccountStatusEnum.findByName(request.getStatus());
    if (accountStatusEnum == null) {
      log.warn("accountStatusEnum {} cannot be find", request.getStatus());
      throw new BjcaBizException(ReturnCodeEnum.REQ_PARAM_ERR);
    }
    entInfo.setStatus(request.getStatus());
    entInfo.setOper(request.getOper());
    int result = 0;
    if (ifExists) {
      entInfo.setId(entInfoList.get(0).getId());
      entInfo.setVersion(entInfoList.get(0).getVersion() + 1);
      result = entInfoMapper.updateByPrimaryKeySelective(entInfo);
    } else {
      entInfo.setVersion(1);
      result = entInfoMapper.insertSelective(entInfo);
    }
    log.info("sync ent result:{}", result);
    response = new EntSyncResponse();
    response.setEid(entInfo.getId());
    return response;
  }
}
