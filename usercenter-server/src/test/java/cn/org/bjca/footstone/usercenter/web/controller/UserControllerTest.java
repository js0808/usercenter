package cn.org.bjca.footstone.usercenter.web.controller;

import cn.org.bjca.footstone.usercenter.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author LvYong
 * @create 2018-03-06
 **/
@Slf4j
public class UserControllerTest extends BaseTest {

    @Test
    public void findById() throws Exception {
        // 请求的url,请求的方法是get
        String responseString = mockMvc.perform(
                get("/bjca/xxxx/v1/users/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()) // 返回的状态是200
                                       .andDo(print()) // 打印出请求和相应的内容
                                       .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串

        log.info("--------返回的json = {}", responseString);
    }

}
