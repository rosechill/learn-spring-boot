package jiwon.webmvc.controller;

import jiwon.webmvc.model.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(
                get("/user/current")
                        .sessionAttr("user", new User("jiwon"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello jiwon"))
        );
    }

    @Test
    public void getUserInvalid() throws Exception {
        mockMvc.perform(
                get("/user/current")
        ).andExpectAll(
                status().is3xxRedirection()
        );
    }
}