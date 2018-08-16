package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.enmus.AccountTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;
import cn.org.bjca.footstone.usercenter.api.enmus.ReturnCodeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.AccountRegisterRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.AuthCodeValidateRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.ResetPasswordRequest;
import cn.org.bjca.footstone.usercenter.dao.mapper.AccountInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfo;
import cn.org.bjca.footstone.usercenter.dao.model.AccountInfoExample;
import cn.org.bjca.footstone.usercenter.exceptions.BjcaBizException;
import cn.org.bjca.footstone.usercenter.util.SHA1MessageDigest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Slf4j
public class AccountRegisterService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private AuthCodeService authCodeService;

    public void accountRegister(AccountRegisterRequest request) throws Exception {
        /**检查帐号是否存在**/
        AccountInfoExample example = new AccountInfoExample();
        example.createCriteria().andAccountEqualTo(request.getAccount());
        List<AccountInfo> infos = accountInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(infos)) {
            throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_EXIT_ERROR);
        }
        /**验证码验证**/
        AuthCodeValidateRequest validateRequest = new AuthCodeValidateRequest();
        validateRequest.setUserName(request.getAccount());
        validateRequest.setAuthCode(request.getAuthCode());
        validateRequest.setType(AuthCodeTypeEnum.REGIST.value());
        authCodeService.validate(validateRequest);
        /**密码处理**/
        String password = SHA1MessageDigest.getInstance()
                .digest(request.getPassword(), SHA1MessageDigest.SALT);
        /**添加帐号信息**/
        AccountInfo info = new AccountInfo();
        Pattern p = Pattern.compile(authCodeService.regexp);
        Matcher m = p.matcher(request.getAccount());
        if (m.matches()) {
            info.setAccountType(AccountTypeEnum.MOBIE.value());
        } else {
            info.setAccountType(AccountTypeEnum.EMAIL.value());
        }
        info.setAccount(request.getAccount());
        info.setPassword(password);
        info.setVersion(1);
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        accountInfoMapper.insertSelective(info);
    }

    public void resetPassword(ResetPasswordRequest request) throws Exception {
        /**检查帐号是否存在**/
        AccountInfoExample example = new AccountInfoExample();
        example.createCriteria().andAccountEqualTo(request.getAccount());
        List<AccountInfo> infos = accountInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(infos)) {
            throw new BjcaBizException(ReturnCodeEnum.ACCOUNT_NOT_EXIT_ERROR);
        }
        AccountInfo info = infos.get(0);
        /**验证码验证**/
        AuthCodeValidateRequest validateRequest = new AuthCodeValidateRequest();
        validateRequest.setUserName(request.getAccount());
        validateRequest.setAuthCode(request.getAuthCode());
        validateRequest.setType(AuthCodeTypeEnum.RESET.value());
        authCodeService.validate(validateRequest);

        String password = SHA1MessageDigest.getInstance()
                .digest(request.getPassword(), SHA1MessageDigest.SALT);
        info.setPassword(password);
        accountInfoMapper.updateByPrimaryKeySelective(info);
    }
}
