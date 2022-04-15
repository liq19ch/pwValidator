package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.validation.EmptyValidation;
import com.innova.pwValidator.prop.validation.LengthValidation;
import com.innova.pwValidator.prop.validation.PatternValidation;
import com.innova.pwValidator.prop.validation.SequenceValidation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = PwValidationServiceImpl.class)
@RunWith(MockitoJUnitRunner.class)
class PwValidationServiceImplTest {

    @Autowired
    private PwValidationServiceImpl pwValidationService;


    @Before
    void init(){
    }


    @Test
    void postConstruct() {
    }

    @Test
    void valid() {
        assertEquals(pwValidationService.valid(""), "password is empty.");
        assertEquals(pwValidationService.valid(null), "password is empty.");
        assertEquals(pwValidationService.valid("abc"), "Password length is invalid. ");
        assertEquals(pwValidationService.valid("123123abc123abc"), "Password length is invalid. ");
        assertEquals(pwValidationService.valid("AZ123A?"),  "password doesn't match to type. ");
        assertEquals(pwValidationService.valid("c__189?"),  "password doesn't match to type. ");
        assertEquals(pwValidationService.valid("cccc"),"password doesn't contain specific type. ");

        assertEquals(pwValidationService.valid("11234abc"),"password is repeated with sequence. ");
//        assertEquals(pwValidationService.valid(""), "Properties setting is invalid in length. ");
//        assertEquals(pwValidationService.valid(""),"password doesn't meet the count of types. ");

    }
}