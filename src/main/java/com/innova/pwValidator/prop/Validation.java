package com.innova.pwValidator.prop;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import javax.annotation.PostConstruct;

public abstract class Validation {

    final Logger logger = LoggerFactory.getLogger(Validation.class);
    private StringBuilder errorMsg = new StringBuilder();


    public abstract boolean isValid(String pw);

    protected void setErrorMsg(String msg) {
        errorMsg.append(msg);
    }

    public String getErrorMsg() {
        return errorMsg.toString();
    }

    protected boolean isEmpty(String pw) {
        if (pw == null || pw.length() < 1) {
            return true;
        }
        return false;
    }

//    public boolean isValidate(String pw) {
//        return isValidLength(pw) && isValidSequence(pw) && isValidType(pw) && isValidCount(pw);
//    }
}


