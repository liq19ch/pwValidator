package com.innova.pwValidator.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmptyValidation extends Validation{
    private final Logger logger = LoggerFactory.getLogger(EmptyValidation.class);

    @Override
    public boolean isValid(String pw) {
        if(isEmpty(pw)){
            logger.info("password is empty");
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMsg() {
        return "password is empty.";
    }
}
