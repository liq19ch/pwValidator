package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.validation.Validation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Validator {

    private List<Validation> validationList = new ArrayList<>();

    public void addValidation(List<Validation> v) {
        this.validationList = v;
    }

    public String validate(String pw) {
        for (Validation validation : validationList) {
            if (!validation.isValid(pw)) {
                return validation.getErrorMsg();
            }
        }
        return null;
    }

    public List<Validation> getValidationList() {
        return validationList;
    }

}