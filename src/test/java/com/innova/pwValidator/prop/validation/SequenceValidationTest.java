package com.innova.pwValidator.prop.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SequenceValidation.class)

class SequenceValidationTest {

    @Autowired
    private SequenceValidation sequenceValidation;

    @Test
    void isValid() {
        Assertions.assertFalse(sequenceValidation.isValid("abab123"));
        Assertions.assertTrue(sequenceValidation.isValid("123ac23ba"));
        Assertions.assertFalse(sequenceValidation.isValid("11"));
        Assertions.assertFalse(sequenceValidation.isValid("11234"));
        Assertions.assertFalse(sequenceValidation.isValid("12323234"));
        Assertions.assertTrue(sequenceValidation.isValid("123242526"));
        Assertions.assertTrue(sequenceValidation.isValid("123121"));
    }

    @Test
    void getErrorMsg() {
        assertEquals(sequenceValidation.getErrorMsg(), "password is repeated with sequence. ");

    }

    @Test
    void isRepeat() {
        Assertions.assertTrue(sequenceValidation.isRepeat("abab123"));
        Assertions.assertFalse(sequenceValidation.isRepeat("123ac23ba"));
        Assertions.assertTrue(sequenceValidation.isRepeat("11"));
        Assertions.assertTrue(sequenceValidation.isRepeat("11234"));
        Assertions.assertTrue(sequenceValidation.isRepeat("12323234"));
        Assertions.assertFalse(sequenceValidation.isRepeat("123242526"));
        Assertions.assertFalse(sequenceValidation.isRepeat("123121"));
    }
}