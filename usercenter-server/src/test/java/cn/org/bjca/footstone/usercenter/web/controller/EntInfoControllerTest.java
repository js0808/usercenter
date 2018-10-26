package cn.org.bjca.footstone.usercenter.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.org.bjca.footstone.usercenter.BaseTest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayQueryRequest;
import cn.org.bjca.footstone.usercenter.api.vo.request.EntPayRequest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/9/20
 * @since 1.0
 **/
@Slf4j
public class EntInfoControllerTest extends BaseTest {

  @Test
  public void payVerify() throws Exception {
    EntPayRequest request = new EntPayRequest();
    request.setAppId("111111");
    request.setUid(82538652999340032L);
    request.setName("陈勇");
    request.setBankAccount("6214680029714530");
    request.setBankAddressCode("BJBJ");
    request.setBankName("北京银行");
    String responseString = mockMvc.perform(
        post("/entinfos/pay").content(JSON.toJSONString(request))
            .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andDo(print()) // 打印出请求和相应的内容
        .andReturn().getResponse().getContentAsString();
    log.info("--------返回的json = {}", responseString);
  }


  @Test
  public void payVerifyQuery() throws Exception {
    EntPayQueryRequest request = new EntPayQueryRequest();
    request.setAppId("111111");
    request.setUid(82538652999340032L);
    request.setQueryTransId("97650829753921536");
    request.setVerifyCode("123456");
    String responseString = mockMvc.perform(
        post("/entinfos/payVerify").content(JSON.toJSONString(request))
            .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andDo(print()) // 打印出请求和相应的内容
        .andReturn().getResponse().getContentAsString();
    log.info("--------返回的json = {}", responseString);
  }

}