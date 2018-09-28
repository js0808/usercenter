package cn.org.bjca.footstone.usercenter.biz.realname;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayRequest;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import cn.org.bjca.footstone.usercenter.vo.IdServiceBaseRespVo;
import cn.org.bjca.footstone.usercenter.vo.IdServiceCheckEntReqVo;
import cn.org.bjca.footstone.usercenter.vo.IdServiceEntPayQueryReqVo;
import cn.org.bjca.footstone.usercenter.vo.IdServiceEntPayReqVo;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @description:封装企业身份核实
 * @author: ZHAOZHIWEI
 * @create: 2018/9/25
 **/
@Slf4j
@Service
public class EntRealNameVerify {

  @Value("${idservice.ent-base}")
  private String checkEntBaseUrl = null;

  @Value("${idservice.ent-pay-verify}")
  private String entPayVerifyUrl = null;

  @Value("${idservice.ent-pay-verify-query}")
  private String entPayVerifyQueryUrl = null;

  public void checkEntBaseInfo(EntInfoRequest entInfoRequest) {
    IdServiceCheckEntReqVo idServiceCheckEntReqVo = new IdServiceCheckEntReqVo();
    idServiceCheckEntReqVo.setEnterpriseName(entInfoRequest.getName());
    idServiceCheckEntReqVo.setLeagalPerson(entInfoRequest.getLegalName());
    idServiceCheckEntReqVo.setBusinessLicenseNo(entInfoRequest.getBizLicense());
    idServiceCheckEntReqVo.setUnCreditCode(entInfoRequest.getSocialCreditCode());
    idServiceCheckEntReqVo.setUnitCode(entInfoRequest.getOrgCode());
    //TYPE=1--企业名称
    idServiceCheckEntReqVo.setKeywordType("1");
    idServiceCheckEntReqVo.setUserTransId(String.valueOf(SnowFlake.next()));
    String reqJson = JSONObject.toJSONString(idServiceCheckEntReqVo);

    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("依赖第三方服务", "身份核实服务", "企业认证");
    ResponseEntity<Map> response = callIds(checkEntBaseUrl, idServiceCheckEntReqVo, reqJson,
        metricsClient);

    if (response.getStatusCode() == HttpStatus.OK) {
      int status = (Integer) response.getBody().get("status");
      String message = (String) response.getBody().get("message");
      if (status == IdServiceBaseRespVo.OK) {
        int resultCode = (Integer) ((Map) (response.getBody().get("data"))).get("resultCode");
        String resultMessage = (String) ((Map) (response.getBody().get("data")))
            .get("resultMessage");

        if (resultCode == IdServiceBaseRespVo.OK) {
          log.info("身份核实服务企业认证成功,请求报文[{}]", reqJson);
          metricsClient.sr_incrSuccess();
        } else {
          //验证不通过
          log.error("身份核实服务验证企业信息不一致，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
          throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, resultMessage);
        }
      } else {
        //请求参数类异常
        log.error("身份核实服务企业认证时错误，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
        throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, message);
      }
    } else {
      log.error("身份核实服务企业认证时发生通信异常,http状态码[{}],请求数据[{}]", response.getStatusCodeValue(), reqJson);
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_CONN_ERROR);
    }
  }

  /**
   * 发起企业打款认证
   */
  public String entPayVerify(String transId, EntPayRequest request) {
    IdServiceEntPayReqVo payReqVo = new IdServiceEntPayReqVo();
    payReqVo.setAccount(request.getBankAccount());
    payReqVo.setAccountName(request.getAccountName());
    payReqVo.setAccountBank(request.getBankName());
    payReqVo.setAccountAddressCode(request.getBankAddressCode());
    //TODO 将来要去掉
    payReqVo.setDeviceId("bjca");
    payReqVo.setUserTransId(transId);
    String reqJson = JSONObject.toJSONString(payReqVo);

    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("依赖第三方服务", "身份核实服务", "发起企业打款");
    ResponseEntity<Map> response = callIds(entPayVerifyUrl, payReqVo, reqJson, metricsClient);

    if (response.getStatusCode() == HttpStatus.OK) {
      int status = (Integer) response.getBody().get("status");
      String message = (String) response.getBody().get("message");
      if (status == IdServiceBaseRespVo.OK) {
        int resultCode = (Integer) ((Map) (response.getBody().get("data"))).get("resultCode");
        String resultMessage = (String) ((Map) (response.getBody().get("data")))
            .get("resultMessage");
        if (resultCode == IdServiceBaseRespVo.OK) {
          //查询时还要使用该transId
          String idsTransId = (String) ((Map) (response.getBody().get("data"))).get("transId");
          log.info("身份核实服务发起打款认证受理成功,请求报文[{}]", reqJson);
          metricsClient.sr_incrSuccess();
          return idsTransId;
        } else {
          //验证不通过
          log.error("身份核实服务发起打款认证受理失败，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
          throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, resultMessage);
        }
      } else {
        //请求参数类异常
        log.error("身份核实服务发起打款认证受理失败，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
        throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, message);
      }

    } else {
      log.error("身份核实服务发起打款认证时发生通信异常,http状态码[{}],请求数据[{}]", response.getStatusCodeValue(), reqJson);
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_CONN_ERROR);
    }
  }


  public Map<String, Object> entPayQuery(String transId, EntPayQueryRequest request) {
    IdServiceEntPayQueryReqVo payQueryReqVo = new IdServiceEntPayQueryReqVo();
    payQueryReqVo.setUserTransId(transId);
    payQueryReqVo.setQueryTransId(request.getQueryTransId());
    payQueryReqVo.setVerifyCode(request.getVerifyCode());
    //TODO 将来要去掉
    payQueryReqVo.setDeviceId("bjca");
    String reqJson = JSONObject.toJSONString(payQueryReqVo);

    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("依赖第三方服务", "身份核实服务", "查询企业打款");
    ResponseEntity<Map> response = callIds(entPayVerifyQueryUrl, payQueryReqVo, reqJson,
        metricsClient);

    if (response.getStatusCode() == HttpStatus.OK) {
      Integer status = (Integer) response.getBody().get("status");
      String message = (String) response.getBody().get("message");
      Map<String, Object> resultMap = new HashMap<>();
      if (status == IdServiceBaseRespVo.OK) {
        Integer resultCode = (Integer) ((Map) (response.getBody().get("data"))).get("resultCode");
        String resultMessage = (String) ((Map) (response.getBody().get("data")))
            .get("resultMessage");
        if (resultCode == IdServiceBaseRespVo.OK) {
          log.info("身份核实服务发起打款认证受理成功,请求报文[{}]", reqJson);
          metricsClient.sr_incrSuccess();
        } else {
          //验证不通过
          log.error("身份核实服务发起打款认证受理失败，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
//          throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, resultMessage);
        }
        resultMap.put("status", resultCode);
        resultMap.put("message", resultMessage);
      } else {
        log.error("身份核实服务发起打款认证受理失败，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
//        throw new BaseException(ReturnCodeEnum.ID_SERVICE_ERROR, message);
        resultMap.put("status", status);
        resultMap.put("message", message);
      }
      return resultMap;
    } else {
      log.error("身份核实服务发起打款认证时发生通信异常,http状态码[{}],请求数据[{}]", response.getStatusCodeValue(), reqJson);
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_CONN_ERROR);
    }
  }


  private ResponseEntity<Map> callIds(String url, Object request, String reqJson,
      MetricsClient metricsClient) {
    ResponseEntity<Map> response = null;
    try {
      response = RestUtils
          .post(url, Map.class, request);
    } catch (Exception e) {
      log.error("身份核实服务通信异常,请求报文[{}]", reqJson, e);
      throw new BaseException(ReturnCodeEnum.ID_SERVICE_CONN_ERROR);
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    return response;
  }
}
