package com.innova.pwValidator.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LengthValidation extends Validation{
    @Autowired
    private PwValidateProp pwValidateProp;

    private final Logger logger = LoggerFactory.getLogger(LengthValidation.class);

    @Override
    public boolean isValid(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        int min = pwValidateProp.getMinLength();
        int max = pwValidateProp.getMaxLength();
        if (min > max) {
            // exc
        }
        if (pw.length() < min || pw.length() > max) {
            return false;
        }
        return true;
    }


}
