package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.enmus.RealNameTypeEnum;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoCheakRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapper;
import cn.org.bjca.footstone.usercenter.dao.model.EntInfo;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
import jodd.bean.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: ZHAOZHIWEI
 * @create: 2018/8/13
 **/
@Slf4j
public class EntInfoService {

  @Autowired
  private EntInfoMapper entInfoMapper;

  @Autowired
  private RestTemplate restTemplate;

  private void addEntInfo(EntInfoRequest entInfoRequest) {

  }

  private void checkRealEntnfo(EntInfoCheakRequest entInfoCheakRequest) {
    String orgCode = entInfoCheakRequest.getOrgCode();
    String bizLicense = entInfoCheakRequest.getBizLicense();
    String socialCreditCode = entInfoCheakRequest.getSocialCreditCode();
    if (StringUtils.isBlank(orgCode) && StringUtils.isBlank(bizLicense) && StringUtils
        .isBlank(socialCreditCode)) {
      throw new BaseException("");
    }
    //目前只支持:ent_base,企业基本信息认证
    String realNameType = entInfoCheakRequest.getRealNameType();
    if (!StringUtils.equals(realNameType, RealNameTypeEnum.ENT_BASE.getDesc())) {

    }
    boolean real = checkRealRemote();
    if (real) {
      EntInfo entInfo = new EntInfo();
      BeanCopy.beans(entInfoCheakRequest, entInfo).copy();
      entInfoMapper.insertSelective(entInfo);
    } else {
      throw new BaseException("");
    }
  }


  private boolean checkRealRemote() {
    return true;
  }
}
