package cn.org.bjca.footstone.usercenter.web.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cn.org.bjca.footstone.usercenter.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/9/20
 * @since 1.0
 **/
@Slf4j
public class AccountControllerTest extends BaseTest {

  @Test
  public void accountCheck() throws Exception {
    // 请求的url,请求的方法是get
    String responseString = mockMvc.perform(
        get("/account/checkAccount")
            .param("account", "18601030948").contentType(MediaType.APPLICATION_FORM_URLENCODED)
    ).andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.data.exist", is(true)))
        .andExpect(jsonPath("$.data.account", is("18601030948")))
        .andDo(print()) // 打印出请求和相应的内容
        .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
    log.info("--------返回的json = {}", responseString);
  }

  @Test
  public void accountCheck1() throws Exception {
    // 请求的url,请求的方法是get
    String responseString = mockMvc.perform(
        get("/account/checkAccount")
            .param("account", "0").contentType(MediaType.APPLICATION_FORM_URLENCODED)
    ).andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.data.exist", is(false)))
        .andDo(print()) // 打印出请求和相应的内容
        .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
    log.info("--------返回的json = {}", responseString);
  }

}