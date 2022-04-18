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


        Assertions.assertTrue(sequenceValidation.isValid("abc123ab"));
        Assertions.assertFalse(sequenceValidation.isValid("abba11"));
        Assertions.assertTrue(sequenceValidation.isValid("abcd1abcd"));
        Assertions.assertFalse(sequenceValidation.isValid("1789caac1798"));
        Assertions.assertTrue(sequenceValidation.isValid("cg161cg6"));
        Assertions.assertTrue(sequenceValidation.isValid("ti8901ti890"));
        Assertions.assertTrue(sequenceValidation.isValid("2134abc2134"));
        Assertions.assertTrue(sequenceValidation.isValid("cacbcac"));
        Assertions.assertTrue(sequenceValidation.isValid("geoi1ioeg"));
        Assertions.assertTrue(sequenceValidation.isValid("cd1465c465"));
    }

    @Test
    void getErrorMessage() {
        assertEquals(sequenceValidation.getErrorMessage(), "input is repeated with sequence.");

    }

    @Test
    void getSuccessMessage() {
        assertEquals(sequenceValidation.getSuccessMessage(), "SequenceValidation is passed.");

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