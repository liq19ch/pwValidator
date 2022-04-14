package com.innova.pwValidator.prop;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

public abstract class Validation implements EmptyBehavior, ErrorBehavior{

    final Logger logger = LoggerFactory.getLogger(Validation.class);

    public abstract boolean isValid(String pw);

    @Override
    public String msg(){
        // customize

        return "";
    }

    @Override
    public boolean isEmpty(String pw) {
        if (pw == null || pw.length() < 1) {
            logger.info("password is empty");
            return true;
        }
        return false;
    }

//    public boolean isValidate(String pw) {
//        return isValidLength(pw) && isValidSequence(pw) && isValidType(pw) && isValidCount(pw);
//    }
}

