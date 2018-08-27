package cn.org.bjca.footstone.usercenter.util;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;

/**
 * Description:MD5、SHA1文摘和HMAC.
 */
public class MessageDigestUtils {

  public static String ENCODING_CHARSET_UTF8 = "UTF-8";
  public static String ENCODING_CHARSET_GBK = "GBK";

//	public static String getTimeStamp() {
//		return String.valueOf(System.currentTimeMillis() / 1000);
//	}

  /**
   * MD5 AND hex，默认UTF8编码
   *
   * @param source 原文
   */
  public static String md5Hex(String source) {
    return md5Hex(source, ENCODING_CHARSET_UTF8);
  }

  /**
   * MD5 AND hex，可以指定MessageDigestUtils.ENCODING_CHARSET_UTF8或MessageDigestUtils.ENCODING_CHARSET_GBK编码。
   *
   * @param source 原文
   * @param charset MessageDigestUtils.ENCODING_CHARSET_UTF8或MessageDigestUtils.ENCODING_CHARSET_GBK
   */
  public static String md5Hex(String source, String charset) {
    if (!StringUtils.equals(charset, ENCODING_CHARSET_GBK)) {
      charset = ENCODING_CHARSET_UTF8;
    }
    byte[] bSource = null;
    try {
      bSource = source.getBytes(charset);
    } catch (Exception ex) {
      bSource = source.getBytes();
    }
    return DigestUtils.md5Hex(bSource);
  }

  /**
   * SHA1 AND hex，默认UTF8编码
   *
   * @param source 原文
   */
  public static String sha1Hex(String source) {
    return sha1Hex(source, ENCODING_CHARSET_UTF8);
  }

  /**
   * SHA1 AND hex，可以指定MessageDigestUtils.ENCODING_CHARSET_UTF8或MessageDigestUtils.ENCODING_CHARSET_GBK编码。
   *
   * @param source 原文
   * @param charset MessageDigestUtils.ENCODING_CHARSET_UTF8或MessageDigestUtils.ENCODING_CHARSET_GBK
   */
  public static String sha1Hex(String source, String charset) {
    if (!StringUtils.equals(charset, ENCODING_CHARSET_GBK)) {
      charset = ENCODING_CHARSET_UTF8;
    }
    byte[] bSource = null;
    try {
      bSource = source.getBytes(charset);
    } catch (Exception ex) {
      bSource = source.getBytes();
    }
    return DigestUtils.sha1Hex(bSource);
  }

  /**
   * 将map中的参数拼接，并做SHA1。字符集：ENCODING_CHARSET_GBK
   *
   * @param signParams 待做hash的参数集合
   */
  public static String createSHA1Sign(SortedMap<String, String> signParams)
      throws Exception {
    StringBuffer sb = new StringBuffer();
    Set<Map.Entry<String, String>> es = signParams.entrySet();
    Iterator<Map.Entry<String, String>> it = es.iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> entry = it.next();
      String k = entry.getKey();
      String v = entry.getValue();
      sb.append(k + "=" + v + "&");
      // 要采用URLENCODER的原始值！
    }
    String params = sb.substring(0, sb.lastIndexOf("&"));
    return sha1Hex(params, ENCODING_CHARSET_GBK);
  }

  /**
   * 通过MD5算法的HMAC
   *
   * @param aValue 原文
   * @param aKey 密钥
   */
  public static String hmacSign(String aValue, String aKey) {
    byte k_ipad[] = new byte[64];
    byte k_opad[] = new byte[64];
    byte keyb[];
    byte value[];
    try {
      keyb = aKey.getBytes(ENCODING_CHARSET_UTF8);
      value = aValue.getBytes(ENCODING_CHARSET_UTF8);
    } catch (UnsupportedEncodingException e) {
      keyb = aKey.getBytes();
      value = aValue.getBytes();
    }

    Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
    Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
    for (int i = 0; i < keyb.length; i++) {
      k_ipad[i] = (byte) (keyb[i] ^ 0x36);
      k_opad[i] = (byte) (keyb[i] ^ 0x5c);
    }
    MessageDigest md = DigestUtils.getMd5Digest();
    md.update(k_ipad);
    md.update(value);
    byte dg[] = md.digest();
    md.reset();
    md.update(k_opad);
    md.update(dg, 0, 16);
    dg = md.digest();
    return Hex.encodeHexString(dg);
  }
}
