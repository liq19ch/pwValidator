package com.innova.pwValidator.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = PwValidationServiceImpl.class)
class PwValidationServiceImplTest {

    @Autowired
    private PwValidationServiceImpl pwValidationService;


    @Test
    void valid() {
        assertEquals(pwValidationService.valid(""), "input is empty.");
        assertEquals(pwValidationService.valid(null), "input is empty.");
        assertEquals(pwValidationService.valid("abc"), "input length is invalid. ");
        assertEquals(pwValidationService.valid("123123abc123abc"), "input length is invalid. ");
        assertEquals(pwValidationService.valid("AZ123A?"),  "input doesn't match to type. ");
        assertEquals(pwValidationService.valid("c__189?"),  "input doesn't match to type. ");
        assertEquals(pwValidationService.valid("ccccc"),"input doesn't contain specific type. ");
        assertEquals(pwValidationService.valid("11234abc"),"input is repeated with sequence. ");

    }
}