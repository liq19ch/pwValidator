package com.innova.pwValidator.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyValidation extends Validation{
    private final Logger logger = LoggerFactory.getLogger(EmptyValidation.class);

    @Override
    public boolean isValid(String pw) {
        if(isEmpty(pw)){
            setErrorMsg("password is empty. ");
            logger.info("password is empty");
            return false;
        }
        return true;
    }
}
