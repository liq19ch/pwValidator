package com.innova.pwValidator.prop;

import java.util.stream.Stream;

public class VerifyRegexConst {

    // at lease one lowercase, one number, length from 5 to 12
    public static final String PATTERN_PASSWORD = "^(?=.*\\d)(?=.*[a-z])[a-z0-9]{5,12}$";

}
