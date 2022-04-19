package com.innova.pwValidator.service;


import com.innova.pwValidator.prop.validation.LengthValidation;
import com.innova.pwValidator.prop.validation.PatternValidation;
import com.innova.pwValidator.prop.validation.SequenceValidation;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PwValidationServiceImpl.class)
class PwValidationServiceImplTest {

    @Autowired
    private PwValidationServiceImpl pwValidationService;

    private String inputEmpty = "input is empty.";
    private String lengthInvalid = "input length is invalid.";
    private String patternNotContain = "input doesn't contain specific type.";
    private String patternNotMatch = "input doesn't match to type.";
    private String sequenceInvalid = "input is repeated with sequence.";
    private String sequencePassed = "SequenceValidation is passed.";
    private String patternPassed = "PatternValidation is passed.";
    private String lengthPassed = "LengthValidation is passed.";

    @Mock
    private LengthValidation lengthValidation;
    @Mock
    private PatternValidation patternValidation;
    @Mock
    private SequenceValidation sequenceValidation;

    @Test
    void init() {
        pwValidationService.init();
    }


    @Test
    void valid() {
        assertEquals(pwValidationService.validate("").getMessage(), new ArrayList<>(Arrays.asList(inputEmpty)));
        assertEquals(pwValidationService.validate(null).getMessage(), new ArrayList<>(Arrays.asList(inputEmpty)));
        assertEquals(pwValidationService.validate("abc").getMessage(), new ArrayList<>(Arrays.asList(lengthInvalid, patternNotContain, sequencePassed)));
        assertEquals(pwValidationService.validate("123123abc123abc").getMessage(), new ArrayList<>(Arrays.asList(lengthInvalid, patternPassed, sequenceInvalid)));
        assertEquals(pwValidationService.validate("AZ123A?").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternNotMatch, sequencePassed)));
        assertEquals(pwValidationService.validate("c__189?").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternNotMatch, sequenceInvalid)));
        assertEquals(pwValidationService.validate("ccccc").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternNotContain, sequenceInvalid)));
        assertEquals(pwValidationService.validate("11234abc").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternPassed, sequenceInvalid)));
    }
}