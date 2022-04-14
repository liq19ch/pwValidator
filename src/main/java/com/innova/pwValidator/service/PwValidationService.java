package com.innova.pwValidator.service;


import com.innova.pwValidator.prop.Validation;

import java.util.List;

public interface PwValidationService {
    boolean isValid(String pw);
    PwValidationService setUp(List<Class<? extends Validation>> validationsList);
}