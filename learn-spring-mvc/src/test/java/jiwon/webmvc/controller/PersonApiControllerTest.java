package jiwon.webmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jiwon.webmvc.model.CreatePersonRequest;
import jiwon.webmvc.model.CreateSocialMediaRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Jiwon");
        request.setMiddleName("Mid");
        request.setLastName("Last");
        request.setEmail("jiwon@example.com");
        request.setPhone("081254079880");
        request.setHobbies(List.of("Coding", "Reading", "Gaming"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "facebook.com/Jiwon"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "instagram.com/Jiwon"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        System.out.println("jsonRequest " + jsonRequest);
        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setMiddleName("Mid");
        request.setLastName("Last");
        request.setHobbies(List.of("Coding", "Reading", "Gaming"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "facebook.com/Jiwon"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "instagram.com/Jiwon"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        System.out.println("jsonRequest " + jsonRequest);
        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validation Error"))
        );
    }

}