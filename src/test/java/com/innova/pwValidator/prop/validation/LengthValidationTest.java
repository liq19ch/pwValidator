package com.innova.pwValidator.prop.validation;

import com.innova.pwValidator.prop.PwValidationSetting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = LengthValidation.class)
class LengthValidationTest {


    @Mock
    private PwValidationSetting setting;
    @Mock
    private LengthValidation lengthValidation;

    @BeforeEach
    void init(){
        setting = new PwValidationSetting();
        setting.setMaxLength(12);
        setting.setMinLength(5);
        lengthValidation = new LengthValidation(setting);
    }


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
        assertEquals(lengthValidation.getErrorMsg(),"");
    }
}