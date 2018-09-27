package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR;
import static java.util.Objects.isNull;

import cn.org.bjca.footstone.usercenter.api.enmus.NotifyTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.RealNameTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoBaseRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoStatusRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntInfoResponse;
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
import jodd.bean.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
   * 修改企业信息并实名认证
   */
  @Transactional(rollbackFor = Exception.class)
  public void updateEntInfo(Long uid, EntInfoRequest entInfoRequest) {
    //目前只支持:ent_base,企业基本信息认证
    String realNameType = entInfoRequest.getRealNameType();
    if (!StringUtils.equals(realNameType, RealNameTypeEnum.ENT_BASE.value())) {
      throw new BaseException(ReturnCodeEnum.REALNAME_TYPE_ERROR);
    }
    //get entinfo by uid
    EntInfo entInfoOld = checkExist(uid);

    //判断状态
    checkStatus(entInfoOld);
    entRealNameVerify.checkEntBaseInfo(entInfoRequest);
    //update ent info
    EntInfo updateEntInfo = new EntInfo();
    BeanCopy.beans(entInfoRequest, updateEntInfo).copy();
    updateEntInfo.setId(entInfoOld.getId());
    updateEntInfo
        .setRealNameType(entInfoOld.getRealNameType() + "," + entInfoRequest.getRealNameType());
    updateEntInfo.setVersion(entInfoOld.getVersion() + 1);
    int result = 0;
    try {
      result = entInfoMapper.updateByPrimaryKeySelective(updateEntInfo);
    } catch (DuplicateKeyException e) {
      log.error("企业信息已经存在，企业名称{}", entInfoOld.getName(), e);
      throw new BaseException(ReturnCodeEnum.RESOURCE_ALREADY_EXIST);
    }
    if (result != 1) {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }
    //保存历史
    saveHistory(entInfoOld);
    //保存notify
    saveUpdateNotifyInfo(uid);
  }

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
    updateEntInfo.setHeadImgUrl(entInfoBaseRequest.getHeadImgUrl());
    updateEntInfo.setPhone(entInfoBaseRequest.getPhone());
    updateEntInfo.setVersion(entInfoOld.getVersion() + 1);
    int result = entInfoMapper.updateByPrimaryKeySelective(updateEntInfo);
    if (result != 1) {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }

    //保存历史
    saveHistory(entInfoOld);
  }

  @Transactional(rollbackFor = Exception.class)
  public void updateEntStatus(Long uid, EntInfoStatusRequest entInfoStatusRequest) {
    //get entinfo by uid
    EntInfo entInfoOld = checkExist(uid);
    checkStatus(entInfoOld);

    //update ent info
    EntInfo updateEntInfo = new EntInfo();
    BeanCopy.beans(entInfoOld, updateEntInfo).copy();
    updateEntInfo.setStatus(entInfoStatusRequest.getStatus().toString());
    updateEntInfo.setVersion(entInfoOld.getVersion() + 1);
    int result = entInfoMapper.updateByPrimaryKeySelective(updateEntInfo);
    if (result != 1) {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }
    //保存历史
    saveHistory(entInfoOld);
  }

  private void saveHistory(EntInfo entInfoHistory) {
    EntInfoHistory history = new EntInfoHistory();
    BeanCopy.beans(entInfoHistory, history).copy();
    history.setId(null);
    history.setRealnameId(entInfoHistory.getId());
    entInfoHistoryMapper.insert(history);
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

  private void checkRealNameParam(String realNameType) {
    if (!StringUtils.equals(realNameType, RealNameTypeEnum.ENT_BASE.value()) ||
        !StringUtils.equals(realNameType, RealNameTypeEnum.ENT_PAY.value())) {
      throw new BaseException(ReturnCodeEnum.REALNAME_TYPE_ERROR);
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

  /**
   * 添加企业并实名认证
   */
  public EntInfoResponse addEntInfo(EntInfoRequest entInfoRequest) {
    checkRealNameParam(entInfoRequest.getRealNameType());
    AccountInfo account = getAccount(entInfoRequest.getUid());
    if (isNull(account)) {
      throw new BaseException(ACCOUNT_NOT_EXIT_ERROR);
    }
    //调用身份核实-企业信息认证
    entRealNameVerify.checkEntBaseInfo(entInfoRequest);
    //保存ent info
    EntInfo entInfo = new EntInfo();
    BeanCopy.beans(entInfoRequest, entInfo).copy();
    entInfo.setRealNameFlag(1);
    try {
      entInfoMapper.insertSelective(entInfo);
    } catch (DuplicateKeyException e) {
      log.error("企业信息已经存在，企业名称{}", entInfoRequest.getName(), e);
      throw new BaseException(ReturnCodeEnum.RESOURCE_ALREADY_EXIST);
    }
    //更新账号表UID
    bindRealNameId(account, entInfo);
    EntInfoResponse response = new EntInfoResponse();
    response.setUid(entInfoRequest.getUid());
    return response;
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
    accountInfoMapper.updateByPrimaryKeySelective(accountInfo);
  }

  public QueryEntInfoResponse queryByAccount(EntInfoQueryRequest request) {
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

  /**
   * 发起打款认证
   */
  public EntPayResponse entPayVerify(EntPayRequest request) {
    //检查账号
    AccountInfo account = getAccount(request.getUid());
    if (isNull(account)) {
      throw new BaseException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
    //uid查询企业用户
    EntInfo entInfo = getEntInfoByUid(request.getUid());
    if (isNull(entInfo)) {
      throw new BaseException(ReturnCodeEnum.USER_NOT_EXIST);
    }
    String transId = String.valueOf(SnowFlake.next());
    EntPayVerifyRequest verifyRequest = new EntPayVerifyRequest();
    verifyRequest.setUid(account.getUid());
    //可能不存在
    verifyRequest.setRealNameId(account.getRealnameId());
    verifyRequest.setAccountName(request.getAccountName());
    verifyRequest.setBankAccount(request.getBankAccount());
    verifyRequest.setBankName(request.getBankName());
    verifyRequest.setBankAddressCode(request.getBankAddressCode());
    //发起打款
    String idsTransId = entRealNameVerify.entPayVerify(transId, request);
    //添加验证记录
    verifyRequest.setIdsTransId(idsTransId);
    verifyRequestMapper.insertSelective(verifyRequest);
    return EntPayResponse.builder().queryTransId(idsTransId).build();
  }

  /**
   * 使用附言中的验证码，验证企业打款
   */
  public void entPayQuery(EntPayQueryRequest request) {
    //检查账号
    AccountInfo account = getAccount(request.getUid());
    if (isNull(account)) {
      throw new BaseException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
    }
    //查询待验证
    EntPayVerifyRequestExample example = new EntPayVerifyRequestExample();
    example.createCriteria().andUidEqualTo(account.getUid())
        .andIdsTransIdEqualTo(request.getQueryTransId());
    List<EntPayVerifyRequest> oldPayReqs = verifyRequestMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(oldPayReqs)) {
      throw new BaseException(ReturnCodeEnum.REAL_NAME_VERIFY_REQ_NOT_EXIST);
    } else {
      String transId = String.valueOf(SnowFlake.next());
      //查询验证码是否正确
      Map<String, Object> resultMap = entRealNameVerify.entPayQuery(transId, request);
      EntPayVerifyRequest oldPayReq = oldPayReqs.get(0);
      EntPayVerifyRequest verifyRequest = new EntPayVerifyRequest();
      Integer status = (Integer) resultMap.get("status");
      String message = (String) resultMap.get("message");
      verifyRequest.setStatus(String.valueOf(status));
      verifyRequest.setMessage(message);
      verifyRequest.setId(oldPayReq.getId());
      //更新
      verifyRequestMapper.updateByPrimaryKeySelective(verifyRequest);

      //返回
      if (status != IdServiceBaseRespVo.OK) {
        throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, message);
      } else {
        //uid查询企业用户
        EntInfo entInfo = getEntInfoByUid(request.getUid());
        if (isNull(entInfo)) {
          throw new BaseException(ReturnCodeEnum.USER_NOT_EXIST);
        }
        //更新企业用户实名信息
        EntInfo updateEntInfo = new EntInfo();
        updateEntInfo.setId(entInfo.getId());
        updateEntInfo
            .setRealNameType(entInfo.getRealNameType() + "," + RealNameTypeEnum.ENT_PAY.value());
        updateEntInfo.setAccountName(oldPayReq.getAccountName());
        updateEntInfo.setBankAccount(oldPayReq.getBankAccount());
        updateEntInfo.setBankName(oldPayReq.getBankName());
        updateEntInfo.setBankAddressCode(oldPayReq.getBankAddressCode());
        updateEntInfo.setVersion(entInfo.getVersion() + 1);
        entInfoMapper.updateByPrimaryKeySelective(updateEntInfo);
      }
    }
  }
}
