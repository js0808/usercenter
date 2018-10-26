package cn.org.bjca.footstone.usercenter.biz.signverify;

import cn.org.bjca.footstone.metrics.client.metrics.MetricsClient;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import cn.org.bjca.footstone.usercenter.util.RestUtils;
import cn.org.bjca.footstone.usercenter.util.SnowFlake;
import cn.org.bjca.footstone.usercenter.vo.GetCertUidReqVo;
import cn.org.bjca.footstone.usercenter.vo.VerifySignReqVo;
import cn.org.bjca.footstone.usercenter.vo.VerifySignRespVo;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
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
  public void verifySignAndCert(String appId, String sign, String source, String userCert,
      String signAlgIdentifier, String certPolicyId) {
    VerifySignReqVo verifySignReqVo = new VerifySignReqVo();
    verifySignReqVo.setTransId(String.valueOf(SnowFlake.next()));
    verifySignReqVo.setSignAlgIdentifier(signAlgIdentifier);
    verifySignReqVo.setAppId(appId);
    verifySignReqVo.setSignValue(sign);
    verifySignReqVo.setOriData(source);
    verifySignReqVo.setBase64Cert(userCert);
    verifySignReqVo.setVerifyCert("true");
    verifySignReqVo.setVerifyCertPolicyId(certPolicyId);

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
    getCertUidReqVo.setTransId(String.valueOf(SnowFlake.next()));
    getCertUidReqVo.setAppId(appId);
    getCertUidReqVo.setBase64Cert(cert);

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
