package cn.org.bjca.footstone.usercenter.api.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LvYong
 * @create 2018-03-06
 **/
@Data
@ApiModel("用户信息")
public class UserResponse {
    @ApiModelProperty("用户ID")
    private Integer id;
    @ApiModelProperty("用户名称")
    private String  name;
}
