package cn.org.bjca.footstone.usercenter.vo;

import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/8/16
 * @since 1.0
 **/
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class BizResultVo {

  @NonNull
  private boolean isSuccess;
  private ReturnCodeEnum code;

}
