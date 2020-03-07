package com.mickey.tech.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Mock测试
 * @author suzhengxiao
 * @date 2020/3/7 14:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MockHttpSessionTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MockHttpSession mockHttpSession;

    @Before
    public void setUp () throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockHttpSession = new MockHttpSession();
    }

    @Test
    public void testHttpSession () throws Exception{
        /* Login */
        MockHttpServletRequestBuilder loginRequestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .param("username", "test")
                .param("password", "123456")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .session(mockHttpSession);
        mockMvc.perform(loginRequestBuilder);

        /* other */
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restful/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .session(mockHttpSession);
        mockMvc.perform(requestBuilder)
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }
}
