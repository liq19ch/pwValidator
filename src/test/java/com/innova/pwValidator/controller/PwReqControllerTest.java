package com.innova.pwValidator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.pwValidator.req.PasswordReq;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PwReqControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void success() throws Exception {
        PasswordReq request = new PasswordReq("12cd32");
        String jsonStr = parseJsonStr(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/pwValidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("success")
                );

    }

    @Test
    void failSequence() throws Exception {
        PasswordReq request = new PasswordReq("abab1231");
        String jsonStr = parseJsonStr(request);
        mockMvc.perform(
                post("/pwValidate")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(content().string("password is repeated with sequence. ")
                );

    }

    @Test
    void failEmpty() throws Exception {
        PasswordReq request = new PasswordReq("");
        String jsonStr = parseJsonStr(request);
        mockMvc.perform(
                post("/pwValidate")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(content().string("password is empty.")
                );

    }

    @Test
    void failNull() throws Exception {
        PasswordReq request = new PasswordReq(null);
        String jsonStr = parseJsonStr(request);
        mockMvc.perform(
                post("/pwValidate")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(content().string("password is empty.")
                );

    }

    @Test
    void failLength() throws Exception {
        PasswordReq request = new PasswordReq("1234asbhjioe1");
        String jsonStr = parseJsonStr(request);
        mockMvc.perform(
                post("/pwValidate")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(content().string("Password length is invalid. ")
                );

    }

    private String parseJsonStr(Object object) throws JsonProcessingException {
        return new ObjectMapper().writer().writeValueAsString(object);
    }
}