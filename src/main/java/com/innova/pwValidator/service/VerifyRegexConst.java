package com.innova.pwValidator.service;

import java.util.stream.Stream;

public class VerifyRegexConst {

    // lowercase, number, length from 5 to 12
    public static final String PATTERN_PASSWORD = "^(?=.*[a-z])(?=.*\\d).{5,12}$";

}
