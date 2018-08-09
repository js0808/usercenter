package cn.org.bjca.footstone.usercenter;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author LvYong
 * @create 2018-03-06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class BaseTest {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc               mockMvc;

    @org.junit.Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(wac).build();
    }

}
