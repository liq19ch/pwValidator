package com.innova.pwValidator.prop.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LengthValidation.class)
@ExtendWith(MockitoExtension.class)
class LengthValidationTest {



    @Autowired
    private LengthValidation lengthValidation;



    @Test
    void isValid() {
        Assertions.assertFalse(lengthValidation.isValid(""));
        Assertions.assertFalse(lengthValidation.isValid(null));
        Assertions.assertFalse(lengthValidation.isValid("abc"));
        Assertions.assertTrue(lengthValidation.isValid("abcd1"));
        Assertions.assertTrue(lengthValidation.isValid("abcd123"));
        Assertions.assertTrue(lengthValidation.isValid("abcd123abcde"));
        Assertions.assertFalse(lengthValidation.isValid("abcd123abcde123"));
    }

    @Test
    void getErrorMessage() {
        assertEquals(lengthValidation.getErrorMessage(),"");
    }

    @Test
    void getSuccessMessage(){
        assertEquals(lengthValidation.getSuccessMessage(),"LengthValidation is passed.");
    }
}