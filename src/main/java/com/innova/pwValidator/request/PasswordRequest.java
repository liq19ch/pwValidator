package com.innova.pwValidator.request;

import java.io.Serializable;

public class PasswordRequest implements Serializable {

    private String password;

    public PasswordRequest(){

    }

    public PasswordRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
