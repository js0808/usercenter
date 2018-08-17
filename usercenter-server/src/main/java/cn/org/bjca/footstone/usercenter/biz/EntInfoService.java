package cn.org.bjca.footstone.usercenter.biz;

import static cn.org.bjca.footstone.usercenter.api.enmus.UserTypeEnum.ENT;
import static java.util.Objects.isNull;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.enmus.RealNameTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoBaseRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoStatusRequest;
import cn.org.bjca.footstone.usercenter.api.vo.response.EntInfoResponse;
import cn.org.bjca.footstone.usercenter.api.vo.response.QueryEntInfoResponse;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.mapper.NotifyInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfoExample;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

  /**
   * 修改企业信息并实名认证
   */
  public void updateEntInfo(Long uid, EntInfoRequest entInfoRequest) {
    checkRealNameParam(entInfoRequest);
    //get entinfo by uid
    EntInfo entInfoOld = getEntInfoByUid(uid);
    if (entInfoOld == null) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_EXIST);
    }
    //TODO 判断状态

    checkRealName(entInfoRequest);
    //update ent info
    BeanCopy.beans(entInfoRequest, entInfoOld).copy();
    entInfoOld.setVersion(entInfoOld.getVersion() + 1);
    int result = entInfoMapper.updateByPrimaryKeySelective(entInfoOld);
    if (result != 1) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_EXIST);
    }

    //TODO 保存消息

  }

  /**
   * 修改无需实名认证的企业信息
   */
  public void updateEntInfoSimple(Long uid, EntInfoBaseRequest entInfoBaseRequest) {
    //get entinfo by uid
    EntInfo entInfoOld = getEntInfoByUid(uid);
    if (entInfoOld == null) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_EXIST);
    }
    //TODO 判断状态
    //update ent info
    EntInfo updateEntInfo = new EntInfo();
    updateEntInfo.setHeadImgUrl(entInfoBaseRequest.getHeadImgUrl());
    updateEntInfo.setPhone(entInfoBaseRequest.getPhone());
    updateEntInfo.setUid(entInfoOld.getUid());
    updateEntInfo.setVersion(entInfoOld.getVersion() + 1);
    int result = entInfoMapper.updateByPrimaryKeySelective(updateEntInfo);
    if (result != 1) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_EXIST);
    }
    //TODO 保存消息

  }

  public void updateEntStatus(Long uid, EntInfoStatusRequest entInfoStatusRequest) {
    //get entinfo by uid
    EntInfo entInfo = getEntInfoByUid(uid);
    if (entInfo == null) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_EXIST);
    }
    entInfo.setStatus(entInfoStatusRequest.getStatus().toString());
    entInfo.setVersion(entInfo.getVersion() + 1);
    int result = entInfoMapper.updateByPrimaryKeySelective(entInfo);
    if (result != 1) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_EXIST);
    }
  }

  /**
   * 通过企业UID查询企业信息
   */
  public EntInfo getEntInfoByUid(Long uid) {
    EntInfoExample entInfoExample = new EntInfoExample();
    entInfoExample.createCriteria().andUidEqualTo(uid);
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
      return null;
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
    entInfoMapper.insertSelective(entInfo);
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

  public EntInfo queryByAccount(EntInfoQueryRequest request) {
    String account = request.getAccount();
    if (Strings.isNullOrEmpty(account)) {
      return null;
    }
    Long uid = getUidFromAccount(account);
    if (isNull(uid)) {
      return null;
    }
    return getEntInfoByUid(uid);
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
