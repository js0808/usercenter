package cn.org.bjca.footstone.usercenter.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SHA1MessageDigest {

    public static final String SALT = "sk8nBnuBccSdtgtqRYMhtPcVuFaSto5r";

    private static SHA1MessageDigest sha1MessageDigest = null;

    private SHA1MessageDigest() {

    }

    public static SHA1MessageDigest getInstance() {
        return InstanceHolder.sha1MessageDigest;
    }

    private static class InstanceHolder {
        private static SHA1MessageDigest sha1MessageDigest = new SHA1MessageDigest();
    }

    public String digest(String source, String salt) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        messageDigest.update(source.getBytes());
        messageDigest.update(salt.getBytes());
        return Hex.encodeHexString(messageDigest.digest()).toLowerCase();
    }

    public String digest(String source) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        messageDigest.update(source.getBytes());
        return Hex.encodeHexString(messageDigest.digest()).toLowerCase();
    }
}
