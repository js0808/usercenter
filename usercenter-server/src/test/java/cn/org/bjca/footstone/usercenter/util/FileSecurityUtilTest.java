package cn.org.bjca.footstone.usercenter.util;

import static org.junit.Assert.assertEquals;

import com.google.common.io.Files;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/20
 * @since 1.0
 */
@Slf4j
public class FileSecurityUtilTest {

  URL resource = FileSecurityUtilTest.class.getClassLoader().getResource("舒威.jpeg");


  @Test
  public void decode() throws IOException, URISyntaxException {
    Path file = Paths.get(resource.toURI());
    Path encode = FileSecurityUtil.encode(new FileInputStream(file.toFile()));
    String encodedStr = new String(Files.toByteArray(encode.toFile()));
    log.info("encode str {} ", encodedStr);
    InputStream input = new FileInputStream(encode.toFile());
    Path decode = FileSecurityUtil.decode(input);
    String decodedStr = new String(Files.toByteArray(decode.toFile()));
    log.info("decodedStr str {} ", decodedStr);

    String old = DigestUtils.md5Hex(new FileInputStream(file.toFile()));
    String newer = DigestUtils.md5Hex(new FileInputStream(decode.toFile()));
    assertEquals(old, newer);
    encode.toFile().delete();
    decode.toFile().delete();
  }


}