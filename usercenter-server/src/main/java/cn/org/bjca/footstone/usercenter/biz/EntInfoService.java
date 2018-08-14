package cn.org.bjca.footstone.usercenter.biz;

import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoCheakRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntInfoRequest;
import cn.org.bjca.footstone.usercenter.dao.mapper.EntInfoMapper;
import cn.org.bjca.footstone.usercenter.exceptions.BaseException;
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

    String realNameType = entInfoCheakRequest.getRealNameType();


  }
}
