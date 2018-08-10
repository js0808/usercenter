package cn.org.bjca.footstone.usercenter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @description:密码生产和验证
 * @author: ZHAOZHIWEI
 * @create: 2018/8/10
 **/

public class PwdUtil {

  private static final int SRC_POS = 0;
  //SHA1摘要值长度20字节
  private static final int SHA1_LENGTH = 20;
  //SHA256摘要值长度32字节
  private static final int SHA256_LENGTH = 32;
  private static final int LEN = 16;
  private static final String SHA1_ALG = "SHA1";
  private static final String SHA256_ALG = "SHA-256";
  private static final int BEGIN_INDEX_SSHA = 6;
  private static final int BEGIN_INDEX_SHA = 5;
  private static final int BEGIN_INDEX_SSHA256 = 9;

  private static final String SHA = "{SHA}";
  private static final String SSHA = "{SSHA}";
  private static final String SSHA256 = "{SSHA256}";

  /**
   * 密码生成
   *
   * @param inData String
   * @return String
   */
  public static String cipher(String inData) throws Exception {
    if (inData == null) {
      return null;
    }

    String random = RandomStringUtils.randomAlphanumeric(LEN);
    MessageDigest md = MessageDigest.getInstance(SHA256_ALG);

    md.update(inData.getBytes());
    md.update(random.getBytes());

    byte[] bPWD = md.digest();
    byte[] bSalt = random.getBytes();
    byte[] finalPWD = new byte[bPWD.length + bSalt.length];
    System.arraycopy(bPWD, SRC_POS, finalPWD, SRC_POS, SHA256_LENGTH);
    System.arraycopy(bSalt, SRC_POS, finalPWD, SHA256_LENGTH, bSalt.length);

    String strHashPWD = Base64.encodeBase64String(finalPWD);
    return SSHA256 + strHashPWD;
  }


  /**
   * 验证密码
   *
   * @param cipherPWD String 存储的密码
   * @param inputPWD String 输入的密码
   * @return boolean
   */
  public static boolean verify(String cipherPWD, String inputPWD)
      throws NoSuchAlgorithmException {
    MessageDigest md = null;
    // hash值
    byte[] shaCode = null;
    // 盐值
    byte[] salt = null;
    // 取出HASH字符
    if (cipherPWD.startsWith(SSHA256)) {
      cipherPWD = cipherPWD.substring(BEGIN_INDEX_SSHA256);
      byte[] cipherPwdByte = Base64.decodeBase64(cipherPWD);
      // 前32字节是SHA-256数据
      shaCode = new byte[SHA256_LENGTH];
      salt = new byte[cipherPwdByte.length - SHA256_LENGTH];
      System.arraycopy(cipherPwdByte, SRC_POS, shaCode, SRC_POS, SHA256_LENGTH);
      System.arraycopy(cipherPwdByte, SHA256_LENGTH, salt, SRC_POS, salt.length);
      md = MessageDigest.getInstance(SHA256_ALG);
    } else if (cipherPWD.startsWith(SSHA)) {
      cipherPWD = cipherPWD.substring(BEGIN_INDEX_SSHA);
      byte[] cipherPwdByte = Base64.decodeBase64(cipherPWD);
      // 前20字节是SHA-1数据
      shaCode = new byte[SHA1_LENGTH];
      salt = new byte[cipherPwdByte.length - SHA1_LENGTH];
      System.arraycopy(cipherPwdByte, SRC_POS, shaCode, SRC_POS, SHA1_LENGTH);
      System.arraycopy(cipherPwdByte, SHA1_LENGTH, salt, SRC_POS, salt.length);
      md = MessageDigest.getInstance(SHA1_ALG);
    } else if (cipherPWD.startsWith(SHA)) {
      cipherPWD = cipherPWD.substring(BEGIN_INDEX_SHA);
      byte[] ldapPwdByte = Base64.decodeBase64(cipherPWD);
      shaCode = ldapPwdByte;
      salt = new byte[SRC_POS];
      md = MessageDigest.getInstance(SHA1_ALG);
    }

    // 把用户输入的密码添加到摘要计算信息
    md.update(inputPWD.getBytes());
    // 把盐值添加到摘要计算信息
    md.update(salt);

    byte[] inputPwdByte = md.digest();
    return MessageDigest.isEqual(shaCode, inputPwdByte);
  }


  public static void main(String args[]) throws Exception {
    System.out.println(verify(cipher("123"), "123"));
  }
}
