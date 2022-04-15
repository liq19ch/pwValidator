package com.innova.pwValidator.prop.validation;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = EmptyValidation.class)
class EmptyValidationTest {

    @Autowired
    private EmptyValidation emptyValidation;

    @BeforeEach
    void init() {
//        emptyValidation = new EmptyValidation();
    }

    @Test
    void isValid() {

        Assertions.assertFalse(emptyValidation.isValid(""));
        Assertions.assertFalse(emptyValidation.isValid(null));
        Assertions.assertTrue(emptyValidation.isValid("abc"));
    }

    @Test
    void getErrorMsg() {
        assertEquals(emptyValidation.getErrorMsg(),"password is empty.");
    }
}