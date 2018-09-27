package cn.org.bjca.footstone.usercenter.util;

import com.google.common.io.Files;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/20
 * @since 1.0
 */
@Slf4j
public abstract class FileSecurityUtil {

  //    private static final byte[] security = "iwantflyawaygod!".getBytes();
  private static final int security = 0x99;

  private static final int SIZE = 1024;

  public static Path encode(InputStream inputStream) throws IOException {
    File tempDir = Files.createTempDir();
    File tempFile = new File(tempDir, NameGenerator.genera());
    byte[] buffer = new byte[SIZE];

    try (BufferedOutputStream aFile = new BufferedOutputStream(new FileOutputStream(tempFile))) {
      for (int read = inputStream.read(buffer, 0, SIZE); read > -1;
          read = inputStream.read(buffer, 0, SIZE)) {
        for (int i = 0; i < read; i++) {
//          security[i & (security.length - 1)]
          buffer[i] = (byte) (buffer[i] ^ security);
        }
        aFile.write(buffer, 0, read);
      }
      log.info("write to {} ", tempFile);
    }
    return tempFile.toPath();
  }

  public static Path decode(InputStream inputStream) throws IOException {
    return encode(inputStream);
  }

}
