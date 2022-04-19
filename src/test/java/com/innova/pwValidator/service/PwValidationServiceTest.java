package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.validation.LengthValidation;
import com.innova.pwValidator.prop.validation.PatternValidation;
import com.innova.pwValidator.prop.validation.SequenceValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PwValidationServiceTest {
    @Mock
    private LengthValidation lengthValidation;
    @Mock
    private PatternValidation patternValidation;
    @Mock
    private SequenceValidation sequenceValidation;

    @InjectMocks
    private PwValidationServiceImpl pwValidationService;

    @BeforeEach
    void init() {
        pwValidationService.init();
    }

    @Test
    void testCase1() {
        String password = "abc";
        when(lengthValidation.isValid(password)).thenReturn(false);
        when(sequenceValidation.isValid(password)).thenReturn(true);
        when(patternValidation.isValid(password)).thenReturn(false);
        assertEquals(pwValidationService.validate(password).getSuccess(), -1);
    }

    @Test
    void testCase2() {
        String password = "123123abc123abc";
        when(lengthValidation.isValid(password)).thenReturn(false);
        when(sequenceValidation.isValid(password)).thenReturn(false);
        when(patternValidation.isValid(password)).thenReturn(true);
        assertEquals(pwValidationService.validate(password).getSuccess(), -1);
    }

    @Test
    void testCase3() {
        String password = "AZ123A?";
        when(lengthValidation.isValid(password)).thenReturn(true);
        when(sequenceValidation.isValid(password)).thenReturn(true);
        when(patternValidation.isValid(password)).thenReturn(false);
        assertEquals(pwValidationService.validate(password).getSuccess(), -1);
    }

    @Test
    void testCase4() {
        String password = "c__189?";
        when(lengthValidation.isValid(password)).thenReturn(true);
        when(sequenceValidation.isValid(password)).thenReturn(true);
        when(patternValidation.isValid(password)).thenReturn(false);
        assertEquals(pwValidationService.validate(password).getSuccess(), -1);
    }



    @Test
    void testCase6() {
        String password = "abc123a";
        when(lengthValidation.isValid(password)).thenReturn(true);
        when(sequenceValidation.isValid(password)).thenReturn(true);
        when(patternValidation.isValid(password)).thenReturn(true);
        assertEquals(pwValidationService.validate(password).getSuccess(), 1);
    }

}
