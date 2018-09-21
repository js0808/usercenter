package cn.org.bjca.footstone.usercenter.biz.signverify;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import cn.org.bjca.footstone.usercenter.vo.GetCertUidReqVo;
import cn.org.bjca.footstone.usercenter.vo.VerifySignReqVo;
import cn.org.bjca.footstone.usercenter.vo.VerifySignRespVo;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/9/21
 **/
@Service
@Slf4j
public class VerifySignCertService {

  @Value("${signService.verifySign-url}")
  private String verifySignUrl = null;

  @Value("${signService.parseCertUid-url}")
  private String parseCertUidUrl = null;

  /**
   * 验证签名和证书
   */
  public void verifySignAndCert(String appId, String sign, String source, String userCert) {
    VerifySignReqVo verifySignReqVo = new VerifySignReqVo();
    verifySignReqVo.setTransId(RandomStringUtils.randomNumeric(32));
    verifySignReqVo.setSignAlgIdentifier("SHA1withRSA");
    verifySignReqVo.setAppId(appId);
    verifySignReqVo.setSignValue(sign);
    verifySignReqVo.setOriData(source);
    verifySignReqVo.setBase64Cert(userCert);
//    verifySignReqVo.setVerifyCert("true");

    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("依赖第三方服务", "基础签名服务", "验证签名和证书");
    ResponseEntity<Map> response = null;
    String reqJson = JSONObject.toJSONString(verifySignReqVo);
    try {
      response = RestUtils
          .post(verifySignUrl, Map.class, verifySignReqVo);
    } catch (Exception e) {
      log.error("验证签名和证书通信异常,请求报文[{}]", reqJson, e);
      throw new BaseException(ReturnCodeEnum.SIGN_SERVICE_CONN_ERROR);
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    if (response.getStatusCode() == HttpStatus.OK) {
      int status = (Integer) response.getBody().get("status");
      String message = (String) response.getBody().get("message");
      if (status == VerifySignRespVo.RESPONSE_OK) {
        log.info("验证签名和证书成功,请求报文[{}]", reqJson);
        metricsClient.sr_incrSuccess();
      } else {
        log.error("验证签名和证书时错误，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
        throw new BaseException(ReturnCodeEnum.VERIFY_SIGN_CERT_ERROR, message);
      }
    } else {
      log.error("验证签名和证书通信异常,http状态码[{}],请求数据[{}]", response.getStatusCodeValue(), reqJson);
      throw new BaseException(ReturnCodeEnum.SIGN_SERVICE_CONN_ERROR);
    }
  }

  public String getCertUid(String cert, String appId) {
    GetCertUidReqVo getCertUidReqVo = new GetCertUidReqVo();
    getCertUidReqVo.setTransId(RandomStringUtils.randomNumeric(32));
    getCertUidReqVo.setAppId(appId);
//    getCertUidReqVo.setBase64Cert(cert);
    getCertUidReqVo.setBase64Cert(
        "MIIFJTCCBA2gAwIBAgIKQAAAAAAAABV0ZTANBgkqhkiG9w0BAQUFADBSMQswCQYDVQQGEwJDTjENMAsGA1UECgwEQkpDQTEYMBYGA1UECwwPUHVibGljIFRydXN0IENBMRowGAYDVQQDDBFQdWJsaWMgVHJ1c3QgQ0EtMjAeFw0xMzA0MTExNjAwMDBaFw0yMzA0MTIxNTU5NTlaMFoxCzAJBgNVBAYTAkNOMRkwFwYDVQQKDBBEU1ZT6K6+5aSH6K+B5LmmMRkwFwYDVQQLDBBEU1ZT6K6+5aSH6K+B5LmmMRUwEwYDVQQDDAzorr7lpIfor4HkuaYwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKHIFxJQVK+BIx6uIG9CHDzCQUABWIsL9td/+t6ySRDyMZqvl0ZkomS6GFfeqcPjqs0/CDnWcyq7nfK3fmMrc9/Mf76p85eUfGxFCT2DPfvdZh0vuWKrHkoyEIlK34COgdYRPZ2yX82YLS9d5KlxG5JyfW2JKJMvpW79LD01iCZNAgMBAAGjggJ3MIICczAfBgNVHSMEGDAWgBT7t9RWF1iMI33V+EIB1O13m1fr6TAMBgNVHQ8EBQMDB/gAMCsGA1UdEAQkMCKADzIwMTMwNDEyMDAwMDAwWoEPMjAyMzA0MTIyMzU5NTlaMAkGA1UdEwQCMAAwga8GA1UdHwSBpzCBpDBtoGugaaRnMGUxCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARCSkNBMRgwFgYDVQQLDA9QdWJsaWMgVHJ1c3QgQ0ExGjAYBgNVBAMMEVB1YmxpYyBUcnVzdCBDQS0yMREwDwYDVQQDEwhjYTRjcmwyNTAzoDGgL4YtaHR0cDovL2xkYXAuYmpjYS5vcmcuY24vY3JsL3B0Y2EvY2E0Y3JsMjUuY3JsMBEGCWCGSAGG+EIBAQQEAwIA/zAqBgtghkgBZQMCATAJCgQbaHR0cDovL2JqY2Eub3JnLmNuL2JqY2EuY3J0MBQGBSpWCwcJBAtKSjEyMzQ1Njc4OTAXBghghkgBhvhEAgQLSkoxMjM0NTY3ODkwGwYIKlaGSAGBMAEEDzAxMTAwMDEwMDA0ODkzMDAaBgYqVgsHAQgEEDE1MENASkoxMjM0NTY3ODkwgbAGA1UdIASBqDCBpTA1BgkqgRwBxTiBFQEwKDAmBggrBgEFBQcCARYaaHR0cDovL3d3dy5iamNhLm9yZy5jbi9jcHMwNQYJKoEcAcU4gRUCMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly93d3cuYmpjYS5vcmcuY24vY3BzMDUGCSqBHAHFOIEVAzAoMCYGCCsGAQUFBwIBFhpodHRwOi8vd3d3LmJqY2Eub3JnLmNuL2NwczANBgkqhkiG9w0BAQUFAAOCAQEADjI8yTkJPNNfjPnHtK3EwHUs1OtvcQWTdCZLlvr6zRiZTScPocvTJfyOX7RaAOhaZ0DzTKjpB/se+X6WUPDmaMaU74x+9KEXK7lyiidl5niiaV5ahV2ykH890je34Q2ILAn6k5b7nKOEoEeoQ0pzthKrTZnlbdm0MNU59Lnu4ePN2F+cyfXowrGiVSw3akglFoyeJ9b317gmsEBQ7VSVLJDhkcPyH4ygzwrMIzD9+e2fYJFyjJ4C+EdyV2mtk+3nOpeGasQtYKxaqiaqpg8eJP86tIKhWBnPzIr7Z4LN96+k8kkUJyTl0pQ+9lXmBF2pja5TrHkQNmdrp7+nW+5Mcg==");

    //埋点
    MetricsClient metricsClient = MetricsClient.newInstance("依赖第三方服务", "基础签名服务", "获取证书唯一标识");
    ResponseEntity<Map> response = null;
    String reqJson = JSONObject.toJSONString(getCertUidReqVo);
    try {
      response = RestUtils
          .post(parseCertUidUrl, Map.class, getCertUidReqVo);
    } catch (Exception e) {
      log.error("获取证书唯一标识通信异常,请求报文[{}]", reqJson, e);
      throw new BaseException(ReturnCodeEnum.SIGN_SERVICE_CONN_ERROR);
    } finally {
      metricsClient.qps().rt().sr_incrTotal();
    }
    String certUid = null;
    if (response.getStatusCode() == HttpStatus.OK) {
      int status = (Integer) response.getBody().get("status");
      String message = (String) response.getBody().get("message");
      if (status == VerifySignRespVo.RESPONSE_OK) {
        log.info("获取证书唯一标识成功,请求报文[{}]", reqJson);
        certUid = (String) ((Map) (response.getBody().get("data"))).get("result");
        metricsClient.sr_incrSuccess();
      } else {
        log.error("获取证书唯一标识时错误，请求报文[{}],响应报文[{}]", reqJson, JSONObject.toJSON(response));
        throw new BaseException(ReturnCodeEnum.PARSE_CERT_UID_ERROR, message);
      }
    } else {
      log.error("获取证书唯一标识通信异常,http状态码[{}],请求数据[{}]", response.getStatusCodeValue(), reqJson);
      throw new BaseException(ReturnCodeEnum.SIGN_SERVICE_CONN_ERROR);
    }
    return certUid;
  }

}
