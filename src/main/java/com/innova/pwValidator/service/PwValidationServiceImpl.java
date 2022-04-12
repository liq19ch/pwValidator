package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.Rule;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;


@Scope("prototype")
@Component
public class PwValidationServiceImpl implements PwValidationService {

    @Autowired
    private Rule rule;

    private final Logger logger = LoggerFactory.getLogger(PwValidationServiceImpl.class);

    @Override
    public boolean isValid(String pw) {

        logger.info("validate password=>{} ", rule);

        if (!rule.isValidate(pw)) {
            logger.info(pw + " is invalid");
            return false;
        }
        return true;
    }

}
