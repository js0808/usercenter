package cn.org.bjca.footstone.usercenter.api.vo.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/9/20
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageUploadRequest extends BaseRequest {

  @NotNull(message = "文件不能为空")
  private MultipartFile image;
  
  @NotNull
  @Min(1)
  private Long uid;


}
