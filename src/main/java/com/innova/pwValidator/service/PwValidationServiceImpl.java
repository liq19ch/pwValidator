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
    public PasswordResponse validate(String pw) {
        PasswordResponse passwordRes = new PasswordResponse();
        if (pw == null || pw.isEmpty()) {
            passwordRes.getMessage().add("input is empty.");
            passwordRes.setSuccess(-1);
            return passwordRes;
        }
        int res = 0;
        for (Validation validation : validationList) {
            if (!validation.isValid(pw)) {
                passwordRes.getMessage().add(validation.getErrorMessage());
                res = -1;
            } else {
                passwordRes.getMessage().add(validation.getSuccessMessage());
            }
        }
        passwordRes.setSuccess(res);
        return passwordRes;
    }
}
