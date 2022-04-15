package com.innova.pwValidator.prop.validation;


import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public abstract class Validation {

    final Logger logger = LoggerFactory.getLogger(Validation.class);


    public abstract boolean isValid(String str);


    public abstract String getErrorMsg();

    protected boolean isEmpty(String str) {
        if (pw == null || pw.equals("")) {
            return true;
        }
        return false;
    }

//    public boolean isValidate(String pw) {
//        return isValidLength(pw) && isValidSequence(pw) && isValidType(pw) && isValidCount(pw);
//    }
}


