package cn.org.bjca.footstone.usercenter.api.vo.request;

import cn.org.bjca.footstone.usercenter.api.enmus.UserInfoStatusEnum;
import lombok.Data;

/**
 * @description:企业状态修改
 * @author: ZHAOZHIWEI
 * @create: 2018/8/16
 **/
@Data
public class EntInfoStatusRequest {

  private UserInfoStatusEnum status;
}
