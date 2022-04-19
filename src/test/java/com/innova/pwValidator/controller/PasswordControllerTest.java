package com.innova.pwValidator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.pwValidator.request.PasswordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PasswordControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private void flow(PasswordRequest req, String res) throws Exception {
        String jsonStr = parseJsonStr(req);
        mockMvc.perform(MockMvcRequestBuilders.post("/pwValidate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(res)
                );

    }



    @Test
    void success() throws Exception {
        PasswordRequest req = new PasswordRequest("12cd32");
        flow(req, "{'success':0,'message':['LengthValidation is passed.','PatternValidation is passed.','SequenceValidation is passed.']}");
    }


    @Test
    void failCase1() throws Exception {
        PasswordRequest req = new PasswordRequest("abab1231");
        flow(req, "{'success':-1,'message':['LengthValidation is passed.','PatternValidation is passed.','input is repeated with sequence.']}");
    }



    @Test
    void failCase2() throws Exception {
        PasswordRequest req = new PasswordRequest("");
        flow(req, "{'success':-1,'message':['input is empty.']}");
    }

    @Test
    void failCase3() throws Exception {
        PasswordRequest req = new PasswordRequest(null);
        flow(req, "{'success':-1,'message':['input is empty.']}");
    }

    @Test
    void failCase4() throws Exception {
        PasswordRequest req = new PasswordRequest("1234asbhjioe1");
        flow(req, "{'success':-1,'message':['input length is invalid.','PatternValidation is passed.','SequenceValidation is passed.']}");
    }

    private String parseJsonStr(Object object) throws JsonProcessingException {
        return new ObjectMapper().writer().writeValueAsString(object);
    }
}