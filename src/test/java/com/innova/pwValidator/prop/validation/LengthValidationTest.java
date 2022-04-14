package com.innova.pwValidator.prop.validation;

import com.innova.pwValidator.prop.PwValidationSetting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = LengthValidation.class)
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
    void getErrorMsg() {
    }
}