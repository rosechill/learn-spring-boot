package jiwon.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Jiwon")
                        .param("middleName", "Mid")
                        .param("lastName", "Last")
                        .param("email", "jiwon@example.com")
                        .param("phone", "081254079880")
                        .param("address.street", "Jalan jalan")
                        .param("address.city", "Jakarta")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "11111")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "facebook.com/Jiwon")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "instagram.com/Jiwon")


        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString(
                        "Success create person Jiwon Mid Last " +
                                "with email jiwon@example.com and phone 081254079880 " +
                                "with address Jalan jalan, Jakarta, Indonesia, 11111"
                ))
        );
    }

}