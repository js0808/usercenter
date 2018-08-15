package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.enmus.RealNameTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoCheakRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.vo.IdServiceBaseRespVo;
import cn.org.bjca.footstone.usercenter.vo.IdServiceCheckEntReqVo;
import cn.org.bjca.footstone.usercenter.vo.IdServiceCheckEntRespVo;
import com.alibaba.fastjson.JSONObject;
import jodd.bean.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @description:企业信息管理
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Slf4j
@Service
public class EntInfoService {

  @Value("${idservice.checkEnterprise}")
  private String checkEntUrl = null;

  @Value("${idservice.userName}")
  private String userName = null;

  @Value("${idservice.password}")
  private String password = null;

  @Autowired
  private EntInfoMapper entInfoMapper;

  @Autowired
  private RestTemplate restTemplate;

  private void addEntInfo(EntInfoRequest entInfoRequest) {

  }

  /**
   * 企业实名认证
   */
  private void checkRealEntnfo(EntInfoCheakRequest entInfoCheakRequest) {
    String orgCode = entInfoCheakRequest.getOrgCode();
    String bizLicense = entInfoCheakRequest.getBizLicense();
    String socialCreditCode = entInfoCheakRequest.getSocialCreditCode();
    if (StringUtils.isBlank(orgCode) && StringUtils.isBlank(bizLicense) && StringUtils
        .isBlank(socialCreditCode)) {
      throw new BaseException(ReturnCodeEnum.ENT_INFO_NOT_ENOUGH);
    }
    //目前只支持:ent_base,企业基本信息认证
    String realNameType = entInfoCheakRequest.getRealNameType();
    if (!StringUtils.equals(realNameType, RealNameTypeEnum.ENT_BASE.getDesc())) {
      throw new BaseException(ReturnCodeEnum.REALNAME_TYPE_ERROR);
    }
    IdServiceCheckEntReqVo idServiceCheckEntReqVo = new IdServiceCheckEntReqVo();
    idServiceCheckEntReqVo.setUserName(userName);
    idServiceCheckEntReqVo.setPassword(password);
    idServiceCheckEntReqVo.setLeagalPerson(entInfoCheakRequest.getLegalName());
    idServiceCheckEntReqVo.setBusinessLicenseNo(entInfoCheakRequest.getBizLicense());
    idServiceCheckEntReqVo.setUnCreditCode(entInfoCheakRequest.getSocialCreditCode());
    idServiceCheckEntReqVo.setUnitCode(entInfoCheakRequest.getOrgCode());
    idServiceCheckEntReqVo.setKeywordType("1");
    idServiceCheckEntReqVo.setTransactionId("");
    //调用身份核实
    checkRealRemote(idServiceCheckEntReqVo);

    EntInfo entInfo = new EntInfo();
    BeanCopy.beans(entInfoCheakRequest, entInfo).copy();
    entInfoMapper.insertSelective(entInfo);
  }

  /**
   * 调用身份核实
   */
  private void checkRealRemote(IdServiceCheckEntReqVo idServiceCheckEntReqVo) {
    String reqJson = JSONObject.toJSONString(idServiceCheckEntReqVo);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("依赖第三方服务", "身份核实服务", "企业认证");
    HttpEntity<String> requestEntity = new HttpEntity(reqJson, headers);
    ResponseEntity<IdServiceCheckEntRespVo> response = null;
    try {
      response = restTemplate
          .exchange(checkEntUrl, HttpMethod.POST, requestEntity, IdServiceCheckEntRespVo.class);
    } catch (RestClientException e) {
      log.error("身份核实服务通信异常,请求报文[{}]", reqJson, e);
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_CONN_ERROR);
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }

    if (response.getStatusCode() == HttpStatus.OK) {
      String resultCode = response.getBody().getResultCode();
      String resultMessage = response.getBody().getResultMessage();
      if (StringUtils.equals(resultCode, IdServiceBaseRespVo.OK)) {
        String entResult = response.getBody().getEnterpriseResult();
        String entResultMsg = response.getBody().getEnterpriseResultMsg();
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
}
