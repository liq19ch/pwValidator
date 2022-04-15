package com.innova.pwValidator.prop.validation;

import com.innova.pwValidator.req.PasswordReq;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = EmptyValidation.class)
@RunWith(MockitoJUnitRunner.class)
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
    }
}