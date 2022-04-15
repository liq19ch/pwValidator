package com.innova.pwValidator.prop.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmptyValidation extends Validation {
    private final Logger logger = LoggerFactory.getLogger(EmptyValidation.class);

    @Override
    public boolean isValid(String str) {
        if(isEmpty(str)){
            logger.info("Input is empty");
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMsg() {
        return "input is empty.";
    }
}
