package com.example.pjhouduan;

import com.example.pjhouduan.controller.StudentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PjhouduanApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Test
    public void contextLoads() throws Exception {

    }

}
