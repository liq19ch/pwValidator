package com.innova.pwValidator.req;

import java.io.Serializable;

public class PasswordReq implements Serializable {

//    @ValidateAll(pattern = PATTERN_PASSWORD)
    private String password;

    public PasswordReq(){

    }

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
