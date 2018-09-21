package cn.org.bjca.footstone.usercenter.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.core.io.InputStreamResource;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/9/10
 * @since 1.0
 **/
public class CleanupInputStreamResource extends InputStreamResource {

  public CleanupInputStreamResource(File file) throws FileNotFoundException {
    super(new FileInputStream(file) {
      @Override
      public void close() throws IOException {
        super.close();
        Files.delete(file.toPath());
      }
    });
  }
}