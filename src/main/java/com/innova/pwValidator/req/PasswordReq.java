package com.innova.pwValidator.req;

import com.innova.pwValidator.annotation.ValidateAll;

import java.io.Serializable;

import static com.innova.pwValidator.service.VerifyRegexConst.PATTERN_PASSWORD;

public class PasswordReq implements Serializable {

//    @ValidateAll(pattern = PATTERN_PASSWORD)
    private String password;

    public PasswordReq(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
