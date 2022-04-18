package com.innova.pwValidator.prop.validation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.innova")
@PropertySource("classpath:application.properties")
public class LengthValidation implements Validation {

    private final Logger logger = LoggerFactory.getLogger(LengthValidation.class);

    private String errorMessage = "";


    @Value("${pw.length.min}")
    private int min;
    @Value("${pw.length.max}")
    private int max;


    @Override
    public boolean isValid(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (min > max) {
            logger.debug("MinLength =>{} is bigger than MaxLength =>{}", min, max);
            errorMessage = "Properties setting is invalid in length.";
            return false;
        }
        if (str.length() < min || str.length() > max) {
            errorMessage = "input length is invalid.";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getSuccessMessage() {
        return "LengthValidation is passed.";
    }



}
