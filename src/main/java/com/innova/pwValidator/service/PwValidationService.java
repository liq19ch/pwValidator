package com.innova.pwValidator.service;

import java.util.List;

public interface PwValidationService {
    boolean isValid(String pw);
    PwValidationService config(List<Class<? extends Rule>> ruleClasses);
}