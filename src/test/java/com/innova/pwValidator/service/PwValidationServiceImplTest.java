package com.innova.pwValidator.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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


    @Test
    void valid() {
        assertEquals(pwValidationService.valid("").getMessage(), new ArrayList<>(Arrays.asList(inputEmpty)));
        assertEquals(pwValidationService.valid(null).getMessage(), new ArrayList<>(Arrays.asList(inputEmpty)));
        assertEquals(pwValidationService.valid("abc").getMessage(), new ArrayList<>(Arrays.asList(lengthInvalid, patternNotContain, sequencePassed)));
        assertEquals(pwValidationService.valid("123123abc123abc").getMessage(), new ArrayList<>(Arrays.asList(lengthInvalid, patternPassed, sequenceInvalid)));
        assertEquals(pwValidationService.valid("AZ123A?").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternNotMatch, sequencePassed)));
        assertEquals(pwValidationService.valid("c__189?").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternNotMatch, sequenceInvalid)));
        assertEquals(pwValidationService.valid("ccccc").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternNotContain, sequenceInvalid)));
        assertEquals(pwValidationService.valid("11234abc").getMessage(), new ArrayList<>(Arrays.asList(lengthPassed, patternPassed, sequenceInvalid)));

    }
}