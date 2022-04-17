package com.innova.pwValidator.prop.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.innova")
public class LengthValidation extends Validation {

    private final Logger logger = LoggerFactory.getLogger(LengthValidation.class);

    private String errorMsg = "";

    private int min;
    private int max;


    public LengthValidation(PwValidationSetting setting) {
        this.min = setting.getMinLength();
        this.max = setting.getMaxLength();
    }

    @Override
    public boolean isValid(String str) {
        if (isEmpty(str)) {
            return false;
        }

        if (min > max) {
            logger.debug("MinLength =>{} is bigger than MaxLength =>{}", min, max);
            errorMsg = "Properties setting is invalid in length. ";
            return false;
        }
        if (str.length() < min || str.length() > max) {
            errorMsg = "input length is invalid. ";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }


}
