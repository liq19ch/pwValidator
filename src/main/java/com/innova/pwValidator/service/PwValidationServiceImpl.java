package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.validation.EmptyValidation;
import com.innova.pwValidator.prop.validation.LengthValidation;
import com.innova.pwValidator.prop.validation.PatternValidation;
import com.innova.pwValidator.prop.validation.SequenceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@ComponentScan("com.innova")
public class PwValidationServiceImpl implements PwValidationService {

    @Autowired
    private Validator validator;
    @Autowired
    private EmptyValidation emptyValidation;
    @Autowired
    private LengthValidation lengthValidation;
    @Autowired
    private PatternValidation patternValidation;
    @Autowired
    private SequenceValidation sequenceValidation;


    @PostConstruct
    public void postConstruct() {
        validator.addValidation(emptyValidation);
        validator.addValidation(lengthValidation);
        validator.addValidation(patternValidation);
        validator.addValidation(sequenceValidation);
    }

    @Override
    public String valid(String pw) {
        return validator.validate(pw);
    }
}
