package cn.org.bjca.footstone.usercenter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:签名工具类，可以对对象、二进制数据、字符串做签名
 */
@SuppressWarnings("all")
@Slf4j
public class SignatureUtils {

  private static Logger logger = LoggerFactory.getLogger(SignatureUtils.class);
  //私钥类型文件
  public static final String PRIVATE_KEY_FILE_PKCS12 = "PKCS12";
  public static final String PRIVATE_KEY_FILE_JKS = "JKS";
  //签名算法
  public static final String SIGN_ALGORITHMS_SHA1RSA = "SHA1WithRSA";
  public static final String SIGN_ALGORITHMS_SHA256RSA = "SHA256withRSA";
  public static final String SIGN_ALGORITHMS_MD5RSA = "MD5WithRSA";
  public static final String SIGN_ALGORITHMS_HMAC = "HmacSHA256";
  public static final String SIGN_ALGORITHMS_HMACSHA256 = "HmacSHA256";
  //签名类型
  public static final String ALGORITHMS_RSA = "RSA";
  public static final String ENCODING_CHARSET_UTF8 = "UTF-8";
  //HASH算法
  public static final String HASH_TYPE_MD5 = "MD5";
  public static final String HASH_TYPE_SHA1 = "SHA1";

  //计算签名时忽略的字段名字:sign/signType/hashType
  public static final String DEFAULT_SIGN_FIELD_NAME = "sign";
  public static final String DEFAULT_SIGNMSG_FIELD_NAME = "signMsg";
  public static final String DEFAULT_SIGN_TYPE_FIELD_NAME = "signType";
  //常量字符串
  private static final String AND = "&";
  private static final String EQUAL = "=";
  private static final String NULL_STRING = "";
  private static final String DEFAULT_KEY_PARAM_NAME = "key";


  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  /**
   * 对对象计算签名,签名算法为SHA1WithRSA
   *
   * @param bean 待签名的对象
   * @param signType 签名的类型：SignatureUtils.ALGORITHMS_RSA
   * @param privateKey 签名私钥(base64字符串)
   * @return Base64编码的签名数据
   */
  public static String signatureBean(Object bean, String signType,
      String privateKey) {
    try {
      if (StringUtils.equals(signType, SIGN_ALGORITHMS_SHA256RSA)) {
        PrivateKey priKey = convertPrivateKey(privateKey);
        return sign(generateSignString(bean), priKey, SIGN_ALGORITHMS_SHA1RSA);
      } else if (StringUtils.equals(signType, SIGN_ALGORITHMS_HMAC) || StringUtils
          .equals(signType, SIGN_ALGORITHMS_HMACSHA256)) {
        return HMAC(generateSignString(bean).getBytes(ENCODING_CHARSET_UTF8),
            privateKey.getBytes(ENCODING_CHARSET_UTF8), signType);
      } else {
        throw new RuntimeException("不支持的签名类型参数");
      }
    } catch (Throwable ex) {
      throw new RuntimeException("签名异常!:" + ex.getMessage());
    }
  }

  public static String HMAC(byte[] data, byte[] key, String signType)
      throws NoSuchAlgorithmException, InvalidKeyException {
    SecretKeySpec signingKey = new SecretKeySpec(key, signType);
    Mac mac = Mac.getInstance(signType);
    mac.init(signingKey);
    return Base64.encodeBase64String(mac.doFinal(data));
  }

  public static String byte2hex(byte[] b) {
    StringBuilder hs = new StringBuilder();

    for (int n = 0; b != null && n < b.length; ++n) {
      String stmp = Integer.toHexString(b[n] & 255);
      if (stmp.length() == 1) {
        hs.append('0');
      }
      hs.append(stmp);
    }
    return hs.toString();
  }

  public static String signatureBean(String content, String signType, String privateKey)
      throws Exception {
    try {
      if (StringUtils.equals(signType, ALGORITHMS_RSA)) {
        throw new Exception("不支持的签名类型参数，必须为'RSA'");
      }
      PrivateKey priKey = convertPrivateKey(privateKey);
      return sign(content, priKey, SIGN_ALGORITHMS_SHA1RSA);
    } catch (NoSuchAlgorithmException ex) {
      throw new Exception("不支持的签名算法:" + ex.getMessage());
    } catch (InvalidKeySpecException ike) {
      throw new Exception("密钥数据不正确:" + ike.getMessage());
    } catch (Exception ex) {
      throw new Exception("签名失败:" + ex.getMessage());
    }
  }


  public static String getContent(Object object) throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    String json = mapper.writeValueAsString(object);
    return json;
  }


  /**
   * 对对象计算签名,签名算法为SHA1WithRSA
   *
   * @param bean 待签名的对象
   * @param signType 签名的类型：SignatureUtils.ALGORITHMS_RSA
   * @param privateKey 签名私钥
   * @return Base64编码的签名数据
   */
  public static String signatureBean(Object bean, String signType, PrivateKey privateKey)
      throws Exception {
    try {
      if (StringUtils.equals(signType, ALGORITHMS_RSA)) {
        throw new Exception("不支持的签名类型参数，必须为'RSA'");
      }
      return sign(generateSignString(bean), privateKey, signType);
    } catch (Exception e) {
      throw new Exception("签名失败:" + e.getMessage());
    }
  }

  /**
   * 对二进制数据计算签名,签名算法为SHA1WithRSA
   *
   * @param sourceData 待签名的对象
   * @param signType 签名的类型：SignatureUtils.ALGORITHMS_RSA
   * @param privateKey 签名私钥
   * @return Base64编码的签名数据
   */
  public static String signatureData(byte[] sourceData, String signType,
      PrivateKey privateKey) throws Exception {
    try {
      if (StringUtils.equals(signType, ALGORITHMS_RSA)) {
        throw new Exception("不支持的签名类型参数，必须为'RSA'");
      }
      return sign(sourceData, privateKey, SIGN_ALGORITHMS_SHA1RSA);
    } catch (Exception ex) {
      throw new Exception("签名失败:" + ex.getMessage());
    }
  }

  /**
   * 对字符串计算签名,签名算法为SHA1WithRSA
   *
   * @param sourceData 待签名的字符串
   * @param signType 签名的类型：SignatureUtils.ALGORITHMS_RSA
   * @param privateKey 签名私钥(base64字符串)
   * @return Base64编码的签名数据
   */
  public static String signatureMessage(String sourceData, String signType,
      PrivateKey privateKey) throws Exception {
    try {
      if (StringUtils.equals(signType, ALGORITHMS_RSA)) {
        throw new Exception("不支持的签名类型参数，必须为'RSA'");
      }
      return sign(sourceData.getBytes(ENCODING_CHARSET_UTF8), privateKey, SIGN_ALGORITHMS_SHA1RSA);
    } catch (Exception ex) {
      throw new Exception("签名失败:" + ex.getMessage());
    }
  }

  /**
   * 生成摘要签名
   */
  public static String generateSignString(Object bean, String publicKey, String hashType,
      String[] filterNames) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    Map<String, String> props = paraFilter(mapper.convertValue(bean, Map.class), filterNames);
    return generateSignString(props, publicKey, hashType, filterNames);
  }

  public static String generateSignString(Map<String, String> props, String publicKey,
      String hashType, String[] filterNames) throws Exception {
    StringBuilder sb = new StringBuilder();
    List<String> keys = new ArrayList<String>(props.keySet());
    Collections.sort(keys);

    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      Object value = props.get(key);
      if ((value == null) || (NULL_STRING.equals(value.toString()))) {
        continue;
      }
      if (null != filterNames && isInArray(key, filterNames)) {
        continue;
      }

      sb.append(key).append(EQUAL).append(value).append(AND);

    }
    if (props.get("callId") != null) {
      sb.append("callSecret").append("=").append(publicKey);
    } else {
      sb.append(DEFAULT_KEY_PARAM_NAME).append("=").append(publicKey);
    }
    logger.info("签名串：{}", sb);
    String signStr = "";
    if (HASH_TYPE_MD5.equalsIgnoreCase(hashType)) {
      //使用MD5摘要算法验证签名
      signStr = MessageDigestUtils.md5Hex(sb.toString()).toUpperCase();
    } else if (HASH_TYPE_SHA1.equalsIgnoreCase(hashType)) {
      //使用SH1摘要算法验证签名
      signStr = MessageDigestUtils.sha1Hex(sb.toString()).toUpperCase();
    } else {
      throw new Exception("不支持的签名算法");
    }
    return signStr;
  }

  /**
   * 生成待签名字符串
   *
   * @param bean 待签名的对象
   */
  private static String generateSignString(Object bean) {
    return generateSignString(bean, null);
  }

  /**
   * 生成签名字符串，忽略掉转入的属性名
   */
  private static String generateSignString(Object bean, String[] filterNames) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    Map<String, String> props = paraFilter(mapper.convertValue(bean, Map.class), filterNames);

    StringBuilder sb = new StringBuilder();
    List<String> keys = new ArrayList<String>(props.keySet());
    Collections.sort(keys);

    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      Object value = props.get(key);
      if ((value == null) || (NULL_STRING.equals(value.toString()))) {
        continue;
      }

      if (i == props.size() - 1) {// 拼接时，不包括最后一个&字符
        sb.append(key).append(EQUAL).append(value);
      } else {
        sb.append(key).append(EQUAL).append(value).append(AND);
      }
    }
    log.info("待加密字符串 str=[{}]", sb.toString());
    return sb.toString();
  }

  /**
   * 过滤map中的空字段和非签名字段
   */
  private static Map<String, String> paraFilter(Map<String, Object> sArray) {
    return paraFilter(sArray, null);
  }

  /**
   * 过滤map中的空字段和非签名字段
   */
  private static Map<String, String> paraFilter(Map<String, Object> sArray, String[] filterNames) {
    Map<String, String> result = new HashMap<String, String>();
    if (sArray == null || sArray.size() <= 0) {
      return result;
    }
    for (String key : sArray.keySet()) {
      Object valueObj = sArray.get(key);
      if (null == valueObj) {
        continue;
      }
      String value = sArray.get(key).toString();
      if (filterNames == null) {
        if (StringUtils.isBlank(value)
            || key.equalsIgnoreCase(DEFAULT_SIGN_FIELD_NAME)
            || key.equalsIgnoreCase(DEFAULT_SIGN_TYPE_FIELD_NAME)
            || key.equalsIgnoreCase(DEFAULT_SIGNMSG_FIELD_NAME)) {

          continue;
        }
      } else {
        if (StringUtils.isBlank(value)
            || isInArray(key, filterNames)) {
          continue;
        }

      }
      result.put(key, value);
    }
    return result;
  }

  /**
   * RSA签名
   *
   * @param content 待签名数据
   * @param privateKey BASE64编码的私钥字符串
   * @param signAlg 算法
   * @return 签名值
   */
  private static String sign(byte[] content, PrivateKey privateKey, String signAlg)
      throws Exception {
    Signature signature = Signature
        .getInstance(signAlg);
    signature.initSign(privateKey);
    signature.update(content);
    byte[] signed = signature.sign();
    return Base64.encodeBase64String(signed);
  }

  /**
   * RSA签名
   *
   * @param content 待签名数据
   * @param privateKey 私钥对象
   * @param signAlg 算法
   * @return 签名值
   */
  public static String sign(String content, PrivateKey privateKey, String signAlg)
      throws Exception {
    Signature signature = Signature.getInstance(signAlg);
    signature.initSign(privateKey);
    logger.info("****************************签名前字符串{}", content);
    signature.update(content.getBytes(ENCODING_CHARSET_UTF8));
    byte[] signed = signature.sign();
    return Base64.encodeBase64String(signed);
  }

  /**
   * 校验对象的RSA签名
   *
   * @param bean 签名的对象
   * @param sign Base64编码的签名数据
   * @param publicKey 验签使用的公钥(base64字符串)
   * @param hashType 签名时使用的算法：SignatureUtils.HASH_TYPE_MD5或SignatureUtils.HASH_TYPE_SHA1
   * @return true - 签名验证成功
   */
  public static boolean verifySignatureBean(Object bean, String sign, String publicKey,
      String hashType) throws Exception {
    String content = generateSignString(bean);
    String signAlg = SIGN_ALGORITHMS_SHA1RSA;
    if (HASH_TYPE_MD5.equalsIgnoreCase(hashType)) {
      signAlg = SIGN_ALGORITHMS_MD5RSA;
    }
    return doCheck(content, sign, publicKey, signAlg);
  }

  /**
   * 校验对象的RSA签名，固定使用SHA1WithRSA算法验证
   *
   * @param bean 签名的对象
   * @param sign Base64编码的签名数据
   * @param publicKey 验签使用的公钥(base64字符串)
   * @return true - 签名验证成功
   */
  public static boolean verifySignatureBean(Object bean, String sign, String publicKey)
      throws Exception {
    String content = generateSignString(bean);
    return doCheck(content, sign, publicKey, SIGN_ALGORITHMS_SHA1RSA);
  }

  /**
   * 校验对象的签名，传入签名忽略属性名
   *
   * @param filterNames 忽略属性名称
   */
  public static boolean verifySignatureBean(Object bean, String sign, String publicKey,
      String[] filterNames) throws Exception {
    String content = generateSignString(bean, filterNames);
    return doCheck(content, sign, publicKey, SIGN_ALGORITHMS_SHA1RSA);
  }

  /**
   * 校验对象的RSA签名，固定使用SHA1WithRSA算法验证
   *
   * @param bean 签名的对象
   * @param sign Base64编码的签名数据
   * @param publicKey 验签使用的公钥(base64字符串)
   * @return true - 签名验证成功
   */
  public static boolean verifySignatureBean(Object bean, String sign, PublicKey publicKey,
      String hashType) throws Exception {
    String content = generateSignString(bean);
    String signAlg = SIGN_ALGORITHMS_SHA1RSA;
    if (HASH_TYPE_MD5.equalsIgnoreCase(hashType)) {
      signAlg = SIGN_ALGORITHMS_MD5RSA;
    }
    return doCheck(content.getBytes(ENCODING_CHARSET_UTF8), sign, publicKey, signAlg);
  }

  /**
   * 校验对象的签名，传入签名忽略属性名,支持RAS签名和摘要算法签名 　当签名算法为MD5，SHA1时，使用摘要算法验签 当签名算法为SHA1RSA,MD5RSA时，使用RSA公钥验签，此时sign为Base64编码的签名数据
   *
   * @param bean 签名的对象
   * @param sign 签名数据
   * @param publicKey 验签使用的公钥
   * @param hashType 签名算法
   * @param filterNames 忽略属性名称
   * @return true - 签名验证成功
   */
  public static boolean verifySignatureBean(Object bean, String sign, String publicKey,
      String hashType, String[] filterNames) throws Exception {
    String content = generateSignString(bean, filterNames);
    if (SIGN_ALGORITHMS_SHA1RSA.equalsIgnoreCase(hashType) || SIGN_ALGORITHMS_MD5RSA
        .equalsIgnoreCase(hashType)) {
      //使用RSA签名算法
      return doCheck(content.getBytes(ENCODING_CHARSET_UTF8), sign, publicKey, hashType);
    } else if (HASH_TYPE_MD5.equalsIgnoreCase(hashType)) {
      //使用MD5摘要算法验证签名
      content = content + "&key=" + publicKey;
      logger.info("MD5摘要前字符串[{}]", content);
      String signStr = MessageDigestUtils.md5Hex(content).toUpperCase();
      logger.info("需要加密的字符串内容：{} 加密摘要：{}", content, signStr);
      return sign.equals(signStr);
    } else if (HASH_TYPE_SHA1.equalsIgnoreCase(hashType)) {
      //使用SH1摘要算法验证签名
      content = content + "&key=" + publicKey;
      String signStr = MessageDigestUtils.sha1Hex(content).toUpperCase();
      return sign.equals(signStr);
    } else {
      throw new Exception("不支持的签名算法");
    }
  }

  /**
   * 校验二进制数据的RSA签名
   *
   * @param source 签名的原数据
   * @param sign Base64编码的签名数据
   * @param publicKey 验签使用的公钥
   * @param hashType 签名使用的HASH算法，SignatureUtils.HASH_TYPE_MD5 或 SignatureUtils.HASH_TYPE_SHA1
   * @return true - 签名验证成功
   */
  public static boolean verifySignatureDate(byte[] source, String sign, PublicKey publicKey,
      String hashType) throws Exception {
    String signAlg = SIGN_ALGORITHMS_SHA1RSA;
    if (HASH_TYPE_MD5.equalsIgnoreCase(hashType)) {
      signAlg = SIGN_ALGORITHMS_MD5RSA;
    }
    return doCheck(source, sign, publicKey, signAlg);
  }

  /**
   * 校验二进制数据的RSA签名
   *
   * @param source 签名的原数据
   * @param sign Base64编码的签名数据
   * @param publicKey 验签使用的公钥
   * @param hashType 签名使用的HASH算法，SignatureUtils.HASH_TYPE_MD5 或 SignatureUtils.HASH_TYPE_SHA1
   * @return true - 签名验证成功
   */
  public static boolean verifySignatureMessage(String source, String sign, PublicKey publicKey,
      String hashType) throws Exception {
    String signAlg = SIGN_ALGORITHMS_SHA1RSA;
    if (HASH_TYPE_MD5.equalsIgnoreCase(hashType)) {
      signAlg = SIGN_ALGORITHMS_MD5RSA;
    }
    return doCheck(source.getBytes(ENCODING_CHARSET_UTF8), sign, publicKey, signAlg);
  }

  public static boolean verifySignatureMessage(String source, String sign, String publicKey,
      String hashType) throws Exception {
    String signAlg = SIGN_ALGORITHMS_SHA1RSA;
    if (HASH_TYPE_MD5.equalsIgnoreCase(hashType)) {
      signAlg = SIGN_ALGORITHMS_MD5RSA;
    }
    PublicKey pubKey = convertPublicKey(publicKey);
    return doCheck(source.getBytes(ENCODING_CHARSET_UTF8), sign, publicKey, signAlg);
  }

  /**
   * RSA验签名检查
   *
   * @param content 待签名数据
   * @param sign 签名值
   * @param publicKey 支付宝公钥
   * @param signAlg 算法
   * @return true - 签名验证成功
   */
  private static boolean doCheck(String content, String sign, String publicKey, String signAlg)
      throws Exception {
    return doCheck(content.getBytes(ENCODING_CHARSET_UTF8), sign, publicKey, signAlg);
  }

  /**
   * RSA验签名检查
   *
   * @param content 待签名数据
   * @param sign 签名值
   * @param publicKey 支付宝公钥
   * @param signAlg 算法
   * @return true - 签名验证成功
   */
  private static boolean doCheck(byte[] content, String sign, String publicKey, String signAlg)
      throws Exception {
    PublicKey pubKey = convertPublicKey(publicKey);
    return doCheck(content, sign, pubKey, signAlg);
  }

  private static boolean doCheck(byte[] content, String sign, PublicKey publicKey, String signAlg)
      throws Exception {
    try {
      Signature signature = Signature
          .getInstance(signAlg);
      signature.initVerify(publicKey);
      signature.update(content);
      boolean verify = signature.verify(Base64.decodeBase64(sign));
      return verify;
    } catch (NoSuchAlgorithmException ex) {
      throw new Exception("不支持的签名算法:" + ex.getMessage());
    } catch (InvalidKeyException ex) {
      throw new Exception("密钥数据不正确:" + ex.getMessage());
    } catch (Exception e) {
      throw new Exception("验证签名失败:" + e.getMessage());
    }
  }

  /**
   * 将私钥字符串转换成PrivateKey对象
   *
   * @param privateKeyData 私钥字符串（经过base64编码）
   * @return PrivateKey对象
   */
  public static PrivateKey convertPrivateKey(String privateKeyData) throws Exception {
    try {
      byte[] keyBytes = Base64.decodeBase64(privateKeyData);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
      KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHMS_RSA);
      PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
      return privateKey;
    } catch (NoSuchAlgorithmException e) {
      throw new Exception("转换私钥对象失败:" + e.getMessage());
    } catch (InvalidKeySpecException e) {
      throw new Exception("转换私钥对象失败:" + e.getMessage());
    }
  }

  /**
   * 从私钥文件中读取私钥对象
   *
   * @param type 文件类型。SignatureUtils.PRIVATE_KEY_FILE_PKCS12 或 SignatureUtils.PRIVATE_KEY_FILE_JKS
   * @param privateCertPath 文件路径
   * @param alias 私钥别名
   * @param privateCertPwd 密码
   */
  public static PrivateKey getPrivateKeyFromFile(String type, String privateCertPath, String alias,
      String privateCertPwd) throws Exception {
    try {
      // 密钥仓库
      KeyStore ks = null;
      if (StringUtils.equals(type, PRIVATE_KEY_FILE_PKCS12)) {
        ks = KeyStore.getInstance("PKCS12");
      } else {
        ks = KeyStore.getInstance("JKS");
      }
      InputStream ksfis = Thread.currentThread().getContextClassLoader()
          .getResource(privateCertPath).openStream();
      BufferedInputStream ksbufin = new BufferedInputStream(ksfis);

      char[] keyPwd = privateCertPwd.toCharArray();
      ks.load(ksbufin, keyPwd);
      // 从密钥仓库得到私钥
      PrivateKey privateKey = (PrivateKey) ks.getKey(alias, keyPwd);
      return privateKey;
    } catch (Exception e) {
      throw new Exception("读取文件私钥失败:" + e.getMessage());
    }
  }

  /**
   * 从证书文件中获得公钥
   *
   * @param publicCertFile 证书文件
   */
  public static PublicKey getPublicKeyFromFile(String publicCertFile) throws Exception {
    try {
      InputStream inStream = Thread.currentThread().getContextClassLoader()
          .getResource(publicCertFile).openStream();
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      X509Certificate cert = (X509Certificate) cf
          .generateCertificate(inStream);
      // 获得公钥
      PublicKey pubKey = cert.getPublicKey();
      return pubKey;
    } catch (Exception e) {
      throw new Exception("从文件中读取公钥对象失败:" + e.getMessage());
    }
  }

  /**
   * 将公钥字符串转换成PublicKey对象
   *
   * @param publicKeyData 公钥字符串（经过base64编码）
   * @return PublicKey对象
   */
  public static PublicKey convertPublicKey(String publicKeyData) throws Exception {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHMS_RSA);
      byte[] encodedKey = Base64.decodeBase64(publicKeyData);
      PublicKey pubKey = keyFactory
          .generatePublic(new X509EncodedKeySpec(encodedKey));
      return pubKey;
    } catch (NoSuchAlgorithmException e) {
      throw new Exception("转换公钥对象失败:" + e.getMessage());
    } catch (InvalidKeySpecException e) {
      throw new Exception("转换公钥对象失败:" + e.getMessage());
    }
  }

  /**
   * 判断字符串是否在数组内
   */
  private static boolean isInArray(String name, String[] names) {
    boolean isIn = false;
    if (name != null) {
      for (String inname : names) {
        if (name.equalsIgnoreCase(inname)) {
          isIn = true;
          break;
        }
      }
    }
    return isIn;
  }

  /**
   * RSA签名校验
   */
  public static boolean verifyRsa(Object bean, String sign, String publicKey) {
    String content = generateSignString(bean);
    logger.info("签名字符串:<{}>", content);
    return RSASignature.doCheck(content, sign, publicKey);
  }

}
