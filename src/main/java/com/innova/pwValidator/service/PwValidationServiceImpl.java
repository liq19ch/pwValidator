package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.validation.EmptyValidation;
import com.innova.pwValidator.prop.validation.LengthValidation;
import com.innova.pwValidator.prop.validation.PatternValidation;
import com.innova.pwValidator.prop.validation.SequenceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class PwValidationServiceImpl implements PwValidationService {

    @Autowired
    private Validator validator;

    @PostConstruct
    public void postConstruct() {
        validator.addValidation(new EmptyValidation());
        validator.addValidation(new LengthValidation());
        validator.addValidation(new PatternValidation());
        validator.addValidation(new SequenceValidation());
    }

    @Override
    public String valid(String pw) {
        return validator.validate(pw);
    }
}
