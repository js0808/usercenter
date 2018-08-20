package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR;
import static cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum.RESOURCE_NOT_EXIST;
import static cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum.ENT;
import static java.util.Objects.isNull;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.enmus.RealNameTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoBaseRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoStatusRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.UserInfoStatusEnum;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryEntInfoResponse;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoHistoryMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.NotifyInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoHistory;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import cn.org.bjca.footstone.usercenter.vo.IdServiceBaseRespVo;
import cn.org.bjca.footstone.usercenter.vo.IdServiceCheckEntReqVo;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jodd.bean.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:企业信息管理
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Slf4j
@Service
public class EntInfoService {

  @Value("${idservice.check-enterprise}")
  private String checkEntUrl = null;

  @Value("${idservice.user-name}")
  private String userName = null;

  @Value("${idservice.password}")
  private String password = null;

  @Autowired
  private EntInfoMapper entInfoMapper;

  @Autowired
  private NotifyInfoMapper notifyInfoMapper;

  @Autowired
  private AccountInfoMapper accountInfoMapper;

  @Autowired
  private EntInfoHistoryMapper entInfoHistoryMapper;

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
//    checkRealName(entInfoRequest);
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
    //TODO 保存消息

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
    updateEntInfo.setUid(entInfoOld.getUid());
    updateEntInfo.setVersion(entInfoOld.getVersion() + 1);
    int result = entInfoMapper.updateByPrimaryKeySelective(updateEntInfo);
    if (result != 1) {
      throw new BaseException(ReturnCodeEnum.RESOURCE_NOT_EXIST);
    }

    //保存历史
    saveHistory(entInfoOld);
    //TODO 保存消息

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
    entInfoHistoryMapper.insert(history);
  }

  /**
   * 通过企业UID查询企业信息
   */
  private EntInfo getEntInfoByUid(Long uid) {
    EntInfoExample entInfoExample = new EntInfoExample();
    entInfoExample.createCriteria().andUidEqualTo(uid);
    List<EntInfo> entInfoList = entInfoMapper.selectByExample(entInfoExample);
    return entInfoList.isEmpty() ? null : entInfoList.get(0);
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

  private void checkRealNameParam(EntInfoRequest entInfoRequest) {
    String orgCode = entInfoRequest.getOrgCode();
    String bizLicense = entInfoRequest.getBizLicense();
    String socialCreditCode = entInfoRequest.getSocialCreditCode();
    if (StringUtils.isBlank(orgCode) && StringUtils.isBlank(bizLicense) && StringUtils
        .isBlank(socialCreditCode)) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_ENOUGH);
    }
    //目前只支持:ent_base,企业基本信息认证
    String realNameType = entInfoRequest.getRealNameType();
    if (!StringUtils.equals(realNameType, RealNameTypeEnum.ENT_BASE.value())) {
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
    checkRealNameParam(entInfoRequest);
    //调用身份核实
//    checkRealName(entInfoRequest);
    //保存ent info
    EntInfo entInfo = new EntInfo();
    BeanCopy.beans(entInfoRequest, entInfo).copy();
    entInfo.setUid(SnowFlake.next());
    entInfo.setRealNameFlag(1);
    try {
      entInfoMapper.insertSelective(entInfo);
    } catch (DuplicateKeyException e) {
      log.error("企业信息已经存在，企业名称{}", entInfoRequest.getName(), e);
      throw new BaseException(ReturnCodeEnum.RESOURCE_ALREADY_EXIST);
    }
    //TODO 更新账号表UID

    EntInfoResponse response = new EntInfoResponse();
    response.setUid(entInfo.getUid());
    return response;
    //TODO 保存消息

  }

  /**
   * 调用身份核实
   */
  private void checkRealName(EntInfoRequest entInfoRequest) {
    IdServiceCheckEntReqVo idServiceCheckEntReqVo = new IdServiceCheckEntReqVo();
    idServiceCheckEntReqVo.setUserName(userName);
    idServiceCheckEntReqVo.setPassword(password);
    idServiceCheckEntReqVo.setEnterpriseName(entInfoRequest.getName());
    idServiceCheckEntReqVo.setLeagalPerson(entInfoRequest.getLegalName());
    idServiceCheckEntReqVo.setBusinessLicenseNo(entInfoRequest.getBizLicense());
    idServiceCheckEntReqVo.setUnCreditCode(entInfoRequest.getSocialCreditCode());
    idServiceCheckEntReqVo.setUnitCode(entInfoRequest.getOrgCode());
    //TYPE=1--企业名称
    idServiceCheckEntReqVo.setKeywordType("1");
    idServiceCheckEntReqVo.setTransactionId(String.valueOf(SnowFlake.next()));
    String reqJson = JSONObject.toJSONString(idServiceCheckEntReqVo);

    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("依赖第三方服务", "身份核实服务", "企业认证");
    ResponseEntity<Map<String, String>> response = null;
    try {
      response = RestUtils
          .post(checkEntUrl, new ParameterizedTypeReference<Map<String, String>>() {
          }, idServiceCheckEntReqVo);
    } catch (Exception e) {
      log.error("身份核实服务通信异常,请求报文[{}]", reqJson, e);
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_CONN_ERROR);
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }

    if (response.getStatusCode() == HttpStatus.OK) {
      String resultCode = response.getBody().get("resultCode");
      String resultMessage = response.getBody().get("resultMessage");
      if (StringUtils.equals(resultCode, IdServiceBaseRespVo.OK)) {
        String entResult = response.getBody().get("enterpriseResult");
        String entResultMsg = response.getBody().get("enterpriseResultMsg");
        if (StringUtils.equals(entResult, IdServiceBaseRespVo.OK)) {
          log.info("身份核实服务企业认证成功,请求报文[{}]", reqJson);
          metricsClient.sr_incrSuccess();
        } else {
          //验证不通过
          log.error("身份核实服务验证企业信息不一致，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
          throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, entResultMsg);
        }
      } else {
        //请求参数类异常
        log.error("身份核实服务企业认证时错误，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
        throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, resultMessage);
      }
    } else {
      log.error("身份核实服务企业认证时发生通信异常,http状态码[{}],请求数据[{}]", response.getStatusCodeValue(), reqJson);
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_CONN_ERROR);
    }
  }

  public QueryEntInfoResponse queryByAccount(EntInfoQueryRequest request) {
    String account = request.getAccount();
    if (Strings.isNullOrEmpty(account)) {
      return null;
    }
    Long uid = getUidFromAccount(account);
    if (isNull(uid)) {
      throw new BaseException(ACCOUNT_NOT_EXIT_ERROR);
    }
    QueryEntInfoResponse info = getEntInfo(uid);
    if (isNull(info)) {
      throw new BaseException(RESOURCE_NOT_EXIST);
    }
    return info;
  }

  private Long getUidFromAccount(String account) {
    AccountInfoExample example = new AccountInfoExample();
    example.createCriteria().andAccountEqualTo(account);
    List<AccountInfo> accountInfos = accountInfoMapper.selectByExample(example);
    if (accountInfos.isEmpty()) {
      return null;
    }
    AccountInfo accountInfo = accountInfos.get(0);
    if (!Objects.equals(ENT.toString(), accountInfo.getUserType())) {
      return null;
    }
    return accountInfo.getUid();
  }
}
