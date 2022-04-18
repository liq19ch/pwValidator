package com.innova.pwValidator.prop.validation;


import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public interface Validation {

    final Logger logger = LoggerFactory.getLogger(Validation.class);


    public boolean isValid(String str);


    public String getErrorMessage();

    public String getSuccessMessage();
}


