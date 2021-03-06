package cn.org.bjca.footstone.usercenter;

import cn.org.bjca.footstone.usercenter.util.SignatureUtils;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/9/18
 * @since 1.0
 **/
public class OssTest {


  private String APP_ID = "APP_CFFC7069B75241DBBEF84737F452FE2B";
  private String APP_SECRET = "rPLqjpP7wCCSYPAqaiCk9jYhBhOumCLS";
  private String APP_SECRET_VERSION = "1.0";
  private String DEVICE_ID = "DEV_030C5011EDBB494E9DE654D22B19A6C6";

  //  curl -o test.jpg http://localhost:10000/download\?id\=6da8df7e157a4e6eb87c11af2dcfcbd6\&appId\=APP_CFFC7069B75241DBBEF84737F452FE2B\&deviceId\=DEV_030C5011EDBB494E9DE654D22B19A6C6\&version\=1.0\&signAlgo\=HMAC\&signature\=5gCvzqFAHl0KBCSSFWwaZqEHsrhTTVLXXKjCiwTqMj
  @Test
  public void download() throws Exception {
    CloseableHttpClient client = HttpClients.createDefault();
    URIBuilder builder = new URIBuilder("http://localhost:10000/download");
    HashMap<String, String> params = Maps.newHashMap();
    params.put("appId", APP_ID);
    params.put("deviceId", DEVICE_ID);
    params.put("version", APP_SECRET_VERSION);
    params.put("signAlgo", SignatureUtils.SIGN_ALGORITHMS_HMACSHA256);
    params.put("id", "f32b60c187d94fce8d9b8285b97fc0e3");
    String signStr = SignatureUtils
        .signatureBean(params, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, APP_SECRET);
    params.put("signature", signStr);
    for (Entry<String, String> entry : params.entrySet()) {
      builder.setParameter(entry.getKey(), entry.getValue());
    }
    HttpGet request = new HttpGet(builder.build());
    HttpResponse response = client.execute(request);
    HttpEntity entity = response.getEntity();

    int responseCode = response.getStatusLine().getStatusCode();

    // 返回状态码非200证明下载失败
    if (responseCode != HttpStatus.SC_OK) {
      //eg: {"status":10904007,"message":"资源状态异常"} 文件已经过期或被删除
      //eg: {"status":10904005,"message":"验证请求签名错误"} 签名不对
      System.out.println("[ERROR]:" + EntityUtils.toString(entity));
      return;
    }

    System.out.println("Request Url: " + request.getURI());
    System.out.println("Response Code: " + responseCode);

    InputStream is = null;
    FileOutputStream fos = null;
    try {
      is = entity.getContent();
      String filePath = "/tmp/a44987a3df85438a9b912586dc2b1dd2.pdf";
      fos = new FileOutputStream(new File(filePath));
      int inByte;
      while ((inByte = is.read()) != -1) {
        fos.write(inByte);
      }
    } finally {
      if (is != null) {
        is.close();
      }
      if (fos != null) {
        fos.close();
      }
      if (client != null) {
        client.close();
      }
    }
    System.out.println("File Download Completed!!!");
  }


  @Test
  public void download2() throws Exception {
    CloseableHttpClient client = HttpClients.createDefault();
    URIBuilder builder = new URIBuilder("http://beta.isignet.cn:10019/download");
    HashMap<String, String> params = Maps.newHashMap();
    params.put("appId", "APP_493DB260D7DD4BFEA01CAF17FFC99550");
    params.put("token", "751dda2aa865408fbe4c5410f901ecdc");
    params.put("deviceId", "DEV_57AEEEA417B6484FACD2CC29AEA1B938");
    params.put("id", "4d2c92dc620146bab0967bce54cb5ec0");
    String signStr = SignatureUtils
        .signatureBean(params, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, APP_SECRET);
    params.put("signature", signStr);
    for (Entry<String, String> entry : params.entrySet()) {
      builder.setParameter(entry.getKey(), entry.getValue());
    }
    HttpGet request = new HttpGet(builder.build());
    request.setHeader("__bedrock_oauth", "5342d488431b0128acbfa42151ee4a20");

    HttpResponse response = client.execute(request);
    HttpEntity entity = response.getEntity();

    int responseCode = response.getStatusLine().getStatusCode();

    // 返回状态码非200证明下载失败
    if (responseCode != HttpStatus.SC_OK) {
      //eg: {"status":10904007,"message":"资源状态异常"} 文件已经过期或被删除
      //eg: {"status":10904005,"message":"验证请求签名错误"} 签名不对
      System.out.println("[ERROR]:" + EntityUtils.toString(entity));
      return;
    }

    System.out.println("Request Url: " + request.getURI());
    System.out.println("Response Code: " + responseCode);

    InputStream is = null;
    FileOutputStream fos = null;
    try {
      is = entity.getContent();
      String filePath = "/tmp/4d2c92dc620146bab0967bce54cb5ec0.pdf";
      fos = new FileOutputStream(new File(filePath));
      int inByte;
      while ((inByte = is.read()) != -1) {
        fos.write(inByte);
      }
    } finally {
      if (is != null) {
        is.close();
      }
      if (fos != null) {
        fos.close();
      }
      if (client != null) {
        client.close();
      }
    }
    System.out.println("File Download Completed!!!");
  }


  @Test
  public void upload() throws Exception {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadFile = new HttpPost("http://localhost:10000/upload");
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    HashMap<String, String> params = Maps.newHashMap();
    params.put("appId", APP_ID);
    params.put("deviceId", DEVICE_ID);
    params.put("version", APP_SECRET_VERSION);
    params.put("signAlgo", SignatureUtils.SIGN_ALGORITHMS_HMACSHA256);
    params.put("md5", "5342d488431b0128acbfa42151ee4a20");
    String signStr = SignatureUtils
        .signatureBean(params, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, APP_SECRET);
    params.put("signature", signStr);
    for (Entry<String, String> entry : params.entrySet()) {
      builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN);
    }

    File f = new File("/Users/lvyong/Documents/FastDFS.pdf");
    FileInputStream stream = new FileInputStream(f);
    builder.addBinaryBody(
        "file",
        stream,
        ContentType.APPLICATION_OCTET_STREAM,
        f.getName()
    );

    HttpEntity multipart = builder.build();
    uploadFile.setEntity(multipart);
    CloseableHttpResponse response = httpClient.execute(uploadFile);
    HttpEntity responseEntity = response.getEntity();
    System.out.println("response:" + EntityUtils.toString(responseEntity));
  }

  @Test
  public void sign() {
    HashMap<String, String> params = Maps.newHashMap();
    params.put("appId", "APP_99A43C05454F4608B40D724521C36767");
    params.put("deviceId", "DEV_049796F49CAC4B3FB8C7B72758B72E8F");
    params.put("expire", "14000");
    params.put("id", "b8a50fe9956d4c3e810d111351385cb3");
    params.put("signAlgo", SignatureUtils.SIGN_ALGORITHMS_HMACSHA256);
    params.put("version", "1.0");
    String signStr = SignatureUtils
        .signatureBean(params, SignatureUtils.SIGN_ALGORITHMS_HMACSHA256, "SRRGLvqRxECUoFbnP1CAGlmLLEr1cslI");
    System.out.println(signStr);

  }
}
