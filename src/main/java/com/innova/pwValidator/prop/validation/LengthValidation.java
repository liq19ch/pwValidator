package com.innova.pwValidator.prop.validation;

import com.innova.pwValidator.prop.PwValidationSetting;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.innova")
public class LengthValidation extends Validation {

    @Autowired
    private PwValidationSetting pwValidationSetting;

    private final Logger logger = LoggerFactory.getLogger(LengthValidation.class);

    private String errorMsg = "";

    @Override

    public boolean isValid(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        int min = pwValidationSetting.getMinLength();
        int max = pwValidationSetting.getMaxLength();
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
