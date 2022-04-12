package com.innova.pwValidator.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Rule {

    @Autowired
    private PwValidateProp pwValidateProp;

    private final Logger logger = (Logger) LoggerFactory.getLogger(Rule.class);

    private boolean isValidLength(String pw) {

        if (pw == null || pw.length() < 1) {
            logger.info("password is empty");
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

    private boolean isValidType(String pw) {
        List<Type> rules = pwValidateProp.getTypes();
        for (Type rule : rules) {
            Pattern p = Pattern.compile(rule.getPattern());
            if (!p.matcher(pw).matches()) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidCount(String pw) {

        return true;
    }

    private boolean isValidSequence(String pw) {
        return true;
    }


    public boolean isValidate(String pw) {
        return isValidLength(pw) && isValidSequence(pw) && isValidType(pw) &&isValidCount(pw);
    }
}


