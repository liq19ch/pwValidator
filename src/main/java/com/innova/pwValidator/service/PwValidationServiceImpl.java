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

    private Validator validator;
    private EmptyValidation emptyValidation;
    private LengthValidation lengthValidation;
    private PatternValidation patternValidation;
    private SequenceValidation sequenceValidation;

    @Autowired
    public PwValidationServiceImpl(EmptyValidation emptyValidation, LengthValidation lengthValidation,
                                   PatternValidation patternValidation,SequenceValidation sequenceValidation, Validator validator){
        this.emptyValidation = emptyValidation;
        this.lengthValidation = lengthValidation;
        this.patternValidation = patternValidation;
        this.sequenceValidation = sequenceValidation;
        this.validator = validator;
    }



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
