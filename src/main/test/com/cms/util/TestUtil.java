package com.cms.util;

import com.cms.controller.CourseInfoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUtil {

    private MockMvc mockMvc;

    @Before
    public void beforr(){
        mockMvc = MockMvcBuilders.standaloneSetup(new CourseInfoController()).build();
    }

    @Autowired
    private ConfUtil confUtil;

    @Test
    public void contextLoads() throws Exception{
//        RequestBuilder req = get("/courseInfo/queryList");
//        mockMvc.perform("req").andExpect(status().isOk());
    }

    @Test
    public void show(){
        System.out.println(confUtil.getDefault_pwd());
    }
}
