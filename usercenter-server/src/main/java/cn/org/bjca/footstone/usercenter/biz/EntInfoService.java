package cn.org.bjca.footstone.usercenter.biz;

import static java.util.Objects.isNull;

import cn.org.bjca.footstone.usercenter.api.enmus.NotifyTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.RealNameTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoBaseRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoStatusRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntPayResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryEntInfoResponse;
import cn.org.bjca.footstone.usercenter.biz.realname.EntRealNameVerify;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoHistoryMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapperCustom;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntPayVerifyRequestMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.NotifyInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoAccountJoin;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoHistory;
import cn.org.bjca.footstone.usercenter.dao.model.EntPayVerifyRequest;
import cn.org.bjca.footstone.usercenter.dao.model.EntPayVerifyRequestExample;
import cn.org.bjca.footstone.usercenter.dao.model.NotifyInfo;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import cn.org.bjca.footstone.usercenter.vo.IdServiceBaseRespVo;
import cn.org.bjca.footstone.usercenter.vo.NotifyInfoDataVo;
import cn.org.bjca.footstone.usercenter.vo.NotifyInfoVo;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jodd.bean.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @description:企业信息管理
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Slf4j
@Service
public class EntInfoService {

  @Autowired
  private EntInfoMapper entInfoMapper;

  @Autowired
  private NotifyInfoMapper notifyInfoMapper;

  @Autowired
  private AccountInfoMapper accountInfoMapper;

  @Autowired
  private EntInfoHistoryMapper entInfoHistoryMapper;

  @Autowired
  private EntInfoMapperCustom entInfoMapperCustom;

  @Autowired
  private AccountInfoService accountInfoService;

  @Autowired
  private EntRealNameVerify entRealNameVerify;

  @Autowired
  private EntPayVerifyRequestMapper verifyRequestMapper;


  /**
   * 修改无需实名认证的企业信息
   */
  @Transactional(rollbackFor = Exception.class)
  public void updateEntInfoSimple(Long uid, EntInfoBaseRequest entInfoBaseRequest) {
    //get entinfo by uid
    EntInfo entInfoOld = checkExist(uid);
    //判断状态
    checkStatus(entInfoOld);
    //update ent info
    EntInfo updateEntInfo = new EntInfo();
    updateEntInfo.setId(entInfoOld.getId());
    updateEntInfo.setVersion(entInfoOld.getVersion());
    updateEntInfo.setHeadImgUrl(entInfoBaseRequest.getHeadImgUrl());
    updateEntInfo.setPhone(entInfoBaseRequest.getPhone());
    updateEntInfo.setOper(entInfoBaseRequest.getOper());
    updateEntInfoAndIncrementVersion(updateEntInfo);

    //保存历史
    saveHistory(entInfoOld);
    //保存notify
    saveUpdateNotifyInfo(uid);
  }

  @Transactional(rollbackFor = Exception.class)
  public void updateEntStatus(Long uid, EntInfoStatusRequest entInfoStatusRequest) {
    //get entinfo by uid
    EntInfo entInfoOld = checkExist(uid);
    checkStatus(entInfoOld);

    //update ent info
    EntInfo updateEntInfo = new EntInfo();
    updateEntInfo.setId(entInfoOld.getId());
    updateEntInfo.setVersion(entInfoOld.getVersion());
    updateEntInfo.setStatus(entInfoStatusRequest.getStatus().toString());
    updateEntInfo.setOper(entInfoStatusRequest.getOper());
    updateEntInfoAndIncrementVersion(updateEntInfo);
    //保存历史
    saveHistory(entInfoOld);
    //保存notify
    saveUpdateNotifyInfo(uid);
  }

  private void saveHistory(EntInfo entInfoHistory) {
    EntInfoHistory history = new EntInfoHistory();
    BeanCopy.beans(entInfoHistory, history).copy();
    history.setId(null);
    history.setRealnameId(entInfoHistory.getId());
    entInfoHistoryMapper.insertSelective(history);
  }

  /**
   * 通过企业UID查询企业信息
   */
  private EntInfo getEntInfoByUid(Long uid) {
    return entInfoMapperCustom.selectByUid(uid);
  }


  private EntInfo getEntInfoByName(String name) {
    EntInfoExample entInfoExample = new EntInfoExample();
    entInfoExample.createCriteria().andNameEqualTo(name);

    List<EntInfo> entInfoList = entInfoMapper.selectByExample(entInfoExample);
    return entInfoList.isEmpty() ? null : entInfoList.get(0);
  }

  private EntInfo getEntInfoByRealName(String name) {
    EntInfoExample entInfoExample = new EntInfoExample();
    entInfoExample.createCriteria().andNameEqualTo(name).andRealNameFlagEqualTo(1);

    List<EntInfo> entInfoList = entInfoMapper.selectByExample(entInfoExample);
    return entInfoList.isEmpty() ? null : entInfoList.get(0);
  }

  public QueryEntInfoResponse getEntInfo(Long uid) {
    EntInfo entInfo = getEntInfoByUid(uid);
    if (entInfo != null) {
      QueryEntInfoResponse resp = new QueryEntInfoResponse();
      BeanCopy.beans(entInfo, resp).copy();
      return resp;
    } else {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }
  }

  private void checkRealNameParam(EntPayRequest request) {
    if (!StringUtils.equals(request.getRealNameType(), RealNameTypeEnum.ENT_PAY.value())) {
      throw new BaseException(ReturnCodeEnum.REALNAME_TYPE_ERROR);
    }
    //检查账号
    getAccount(request.getUid());
    //查询企业名是否已经实名认证
    EntInfo checkEntInfo = getEntInfoByRealName(request.getName());
    if (!isNull(checkEntInfo)) {
      throw new BaseException(ReturnCodeEnum.ALREADY_REAL_NAME_CHECKED);
    }
  }

  /**
   * 状态不能是注销
   */
  private void checkStatus(EntInfo entInfo) {
    String status = entInfo.getStatus();
    if (StringUtils.equals(status, UserInfoStatusEnum.INVALID.toString())) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_STATUS_ERROR);
    }
  }

  /**
   * 检查是否存在
   */
  private EntInfo checkExist(Long uid) {
    EntInfo entInfo = getEntInfoByUid(uid);
    if (entInfo == null) {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }
    return entInfo;
  }

  private AccountInfo getAccount(Long uid) {
    AccountInfo account = accountInfoService.findAccountInfoByUid(uid);
    if (isNull(account)) {
      throw new BaseException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
    return account;
  }

  private void bindRealNameId(AccountInfo account, EntInfo entInfo) {
    AccountInfo accountInfo = new AccountInfo();
    accountInfo.setId(account.getId());
    accountInfo.setRealnameId(entInfo.getId());
    accountInfo.setUserType(UserTypeEnum.ENT.name());
    accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
  }


  public QueryEntInfoResponse queryEntInfo(EntInfoQueryRequest request) {
    if (StringUtils.isNotBlank(request.getAccount())) {
      return queryByAccount(request);
    } else if (StringUtils.isNotBlank(request.getName())) {
      EntInfo entInfo = getEntInfoByRealName(request.getName());
      if (isNull(entInfo)) {
        throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
      }
      QueryEntInfoResponse resp = new QueryEntInfoResponse();
      BeanCopy.beans(entInfo, resp).copy();
      return resp;
    } else {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }
  }

  private QueryEntInfoResponse queryByAccount(EntInfoQueryRequest request) {
    String account = request.getAccount();
    if (Strings.isNullOrEmpty(account)) {
      return null;
    }
    QueryEntInfoResponse response = getUidFromAccount(account);
    if (isNull(response)) {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }
    return response;
  }

  private QueryEntInfoResponse getUidFromAccount(String account) {
    EntInfoAccountJoin join = entInfoMapperCustom.selectByAccount(account);
    if (isNull(join)) {
      return null;
    }
    QueryEntInfoResponse resp = new QueryEntInfoResponse();
    BeanCopy.beans(join, resp).copy();
    return resp;
  }

  private void saveUpdateNotifyInfo(Long uid) {
    NotifyInfoDataVo notifyInfoDataVo = NotifyInfoDataVo.builder().uid(uid)
        .userType(UserTypeEnum.ENT.name()).build();
    NotifyInfoVo notifyInfoVo = NotifyInfoVo.builder().data(notifyInfoDataVo)
        .type(NotifyTypeEnum.UPDATE.name()).timestamp(new Date()).build();

    NotifyInfo notifyInfo = new NotifyInfo();
    notifyInfo.setUid(uid);
    notifyInfo.setUserType(UserTypeEnum.ENT.name());
    notifyInfo.setNotifyType(NotifyTypeEnum.UPDATE.name());
    notifyInfo.setNotifyMsg(JSONObject.toJSONString(notifyInfoVo));
    notifyInfoMapper.insertSelective(notifyInfo);
  }

  private String processEntPay(int id, int realNameRecordVersion, EntPayRequest request) {
    String transId = String.valueOf(SnowFlake.next());
    EntPayVerifyRequest verifyRequest = new EntPayVerifyRequest();
    verifyRequest.setUid(request.getUid());
    verifyRequest.setRealNameId(id);
    verifyRequest.setRealNameRecordVersion(realNameRecordVersion);
    verifyRequest.setAccountName(request.getName());
    verifyRequest.setBankAccount(request.getBankAccount());
    verifyRequest.setBankName(request.getBankName());
    verifyRequest.setBankAddressCode(request.getBankAddressCode());
    //发起打款
    String idsTransId = entRealNameVerify.entPayVerify(transId, request);
    //添加验证记录
    verifyRequest.setIdsTransId(idsTransId);
    verifyRequestMapper.insertSelective(verifyRequest);
    return idsTransId;
  }

  /**
   * 发起打款认证
   */
  @Transactional(rollbackFor = Exception.class)
  public EntPayResponse entPay(EntPayRequest request) {
    checkRealNameParam(request);
    //uid查询企业用户
    EntInfo entInfo = getEntInfoByUid(request.getUid());

    //第一次做实名认证
    if (isNull(entInfo)) {
      //保存ent info
      EntInfo newEntInfo = new EntInfo();
      BeanCopy.beans(request, newEntInfo).copy();
      entInfoMapper.insertSelective(newEntInfo);
      //调用身份核实-企业信息认证
      entRealNameVerify.checkEntBaseInfo(request);
      String idsTransId = processEntPay(newEntInfo.getId(), entInfo.getVersion(), request);
      return EntPayResponse.builder().queryTransId(idsTransId).build();
    } else {
      //保存历史
      saveHistory(entInfo);
      //保存notify
      saveUpdateNotifyInfo(request.getUid());
      BeanCopy.beans(request, entInfo).copy();
      //重置实名标识
      entInfo.setRealNameFlag(0);
      /** 更新企业信息 */
      updateEntInfoAndIncrementVersion(entInfo);

      //调用身份核实-企业信息认证
      entRealNameVerify.checkEntBaseInfo(request);
      String idsTransId = processEntPay(entInfo.getId(),entInfo.getVersion(), request);
      return EntPayResponse.builder().queryTransId(idsTransId).build();
    }
  }

  /**
   * 更新企业信息并使用version做乐观锁
   * @param record 目标记录
   */
  private void updateEntInfoAndIncrementVersion(EntInfo record) {
    /** example */
    int oldVersion = record.getVersion();
    EntInfoExample example = new EntInfoExample();
    example.createCriteria()
        .andIdEqualTo(record.getId())
        .andVersionEqualTo(oldVersion);
    /** record */
    int newVersion = oldVersion + 1;
    record.setVersion(newVersion);

    final int rows = entInfoMapper.updateByExampleSelective(record, example);
    if (0 == rows) {
      log.error("更新EntInfo失败，oldVersion:{},record:{}", oldVersion, record);
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }
  }

  private EntInfo getEntInfo(int id) {
    return entInfoMapper.selectByPrimaryKey(id);
  }

  private void updateEntPayRealName(EntInfo entInfo, EntPayVerifyRequest verifyRequest) {
    //更新企业用户实名信息
    EntInfo updateEntInfo = new EntInfo();
    updateEntInfo.setId(entInfo.getId());
    updateEntInfo.setRealNameType(RealNameTypeEnum.ENT_PAY.value());
    updateEntInfo.setAccountName(verifyRequest.getAccountName());
    updateEntInfo.setBankAccount(verifyRequest.getBankAccount());
    updateEntInfo.setBankName(verifyRequest.getBankName());
    updateEntInfo.setBankAddressCode(verifyRequest.getBankAddressCode());
    updateEntInfo.setRealNameFlag(1);
    updateEntInfo.setVersion(entInfo.getVersion());

    updateEntInfoAndIncrementVersion(updateEntInfo);
  }

  /**
   * 使用附言中的验证码，验证企业打款，验证通过后用户实名认证通过
   */
  @Transactional(rollbackFor = Exception.class)
  public void entPayQuery(EntPayQueryRequest request) {
    //检查账号
    AccountInfo account = getAccount(request.getUid());
    //查询待验证status=""
    EntPayVerifyRequestExample example = new EntPayVerifyRequestExample();
    example.createCriteria().andUidEqualTo(account.getUid())
        .andIdsTransIdEqualTo(request.getQueryTransId()).andStatusEqualTo("");
    List<EntPayVerifyRequest> oldPayReqs = verifyRequestMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(oldPayReqs)) {
      throw new BaseException(ReturnCodeEnum.REAL_NAME_VERIFY_REQ_NOT_EXIST);
    }
    /** 打款认证请求信息 */
    EntPayVerifyRequest oldPayReq = oldPayReqs.get(0);
    //id查询企业用户
    EntInfo entInfo = getEntInfo(oldPayReq.getRealNameId());
    /** 企业信息已变更？ EntInfo.version =?= EntPayVerifyRequest.realNameRecordVersion */
    if (Objects.nonNull(oldPayReq.getRealNameRecordVersion()) && Objects.nonNull(entInfo.getVersion())
        && oldPayReq.getRealNameRecordVersion().compareTo(entInfo.getVersion()) != 0) {
      throw new BaseException(ReturnCodeEnum.REALNAME_PARAM_ERROR, "企业信息已经变更");
    }
    /** 已认证？ */
    if (entInfo.getRealNameFlag() == 1) {
      throw new BaseException(ReturnCodeEnum.ALREADY_REAL_NAME_CHECKED);
    }

    String transId = String.valueOf(SnowFlake.next());
    //查询验证码是否正确
    Map<String, Object> resultMap = entRealNameVerify.entPayQuery(transId, request);
    EntPayVerifyRequest verifyRequest = new EntPayVerifyRequest();
    Integer status = (Integer) resultMap.get("status");
    String message = (String) resultMap.get("message");
    verifyRequest.setStatus(String.valueOf(status));
    verifyRequest.setMessage(message);
    verifyRequest.setId(oldPayReq.getId());
    verifyRequest.setVersion(oldPayReq.getVersion() + 1);
    //更新打款申请日志
    verifyRequestMapper.updateByPrimaryKeySelective(verifyRequest);
    //返回
    if (status != IdServiceBaseRespVo.OK) {
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, message);
    }

    //更新企业打款用户实名信息
    updateEntPayRealName(entInfo, oldPayReq);
    //更新账号表real name id
    bindRealNameId(account, entInfo);
  }
}
