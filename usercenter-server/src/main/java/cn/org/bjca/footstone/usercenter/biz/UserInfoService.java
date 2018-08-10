package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.vo.response.UserResponse;
import cn.org.bjca.footstone.usercenter.dao.mapper.UserInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfo;
import cn.org.bjca.footstone.usercenter.dao.model.UserInfoExample;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author LvYong
 * @create 2018-03-06
 **/
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserResponse findById(Integer userId) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(userId);
        List<UserInfo> users = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        UserInfo user = users.get(0);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        return userResponse;
    }
}
