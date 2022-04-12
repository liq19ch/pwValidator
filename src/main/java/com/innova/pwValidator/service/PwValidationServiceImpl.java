package com.innova.pwValidator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope("prototype")
@Component
public class PwValidationServiceImpl implements PwValidationService{


    @Override
    public boolean isValid(String pw) {
        return false;
    }

    @Override
    public PwValidationService config(List<Class<? extends Rule>> ruleClasses) {
        return null;
    }
}
