package com.innova.pwValidator.service;

import com.innova.pwValidator.response.PasswordResponse;
import com.innova.pwValidator.prop.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Service
@ComponentScan("com.innova")
public class PwValidationServiceImpl implements PwValidationService {

    @Autowired
    private LengthValidation lengthValidation;
    @Autowired
    private PatternValidation patternValidation;
    @Autowired
    private SequenceValidation sequenceValidation;

    private List<Validation> validationList;

    @PostConstruct
    public void init() {
        validationList = Arrays.asList(lengthValidation, patternValidation, sequenceValidation);
    }


    @Override
    public PasswordResponse valid(String pw) {
        PasswordResponse passwordRes = new PasswordResponse();
        if (pw == null || pw.isEmpty()) {
            passwordRes.getMessage().add("input is empty.");
            return passwordRes;
        }
        for (Validation validation : validationList) {
            if (!validation.isValid(pw)) {
                passwordRes.getMessage().add(validation.getErrorMessage());
            } else {
                passwordRes.getMessage().add(validation.getSuccessMessage());
            }
        }
        return passwordRes;
    }
}
