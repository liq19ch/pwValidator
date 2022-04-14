package com.innova.pwValidator.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LengthValidation extends Validation {
    @Autowired
    private PwValidateProp pwValidateProp;

    private final Logger logger = LoggerFactory.getLogger(LengthValidation.class);

    private String errorMsg = "";

    @Override

    public boolean isValid(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        int min = pwValidateProp.getMinLength();
        int max = pwValidateProp.getMaxLength();
        if (min > max) {
            logger.debug("MinLength =>{} is bigger than MaxLength =>{}", min, max);
            errorMsg = "Properties setting is invalid in length. ";
            return false;
        }
        if (pw.length() < min || pw.length() > max) {
            errorMsg = "Password length is invalid. ";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }


}
