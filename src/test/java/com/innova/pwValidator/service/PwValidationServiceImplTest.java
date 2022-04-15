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
        assertEquals(pwValidationService.valid(""), "password is empty.");
        assertEquals(pwValidationService.valid(null), "password is empty.");
        assertEquals(pwValidationService.valid("abc"), "Password length is invalid. ");
        assertEquals(pwValidationService.valid("123123abc123abc"), "Password length is invalid. ");
        assertEquals(pwValidationService.valid("AZ123A?"),  "password doesn't match to type. ");
        assertEquals(pwValidationService.valid("c__189?"),  "password doesn't match to type. ");
        assertEquals(pwValidationService.valid("ccccc"),"password doesn't contain specific type. ");
        assertEquals(pwValidationService.valid("11234abc"),"password is repeated with sequence. ");

    }
}