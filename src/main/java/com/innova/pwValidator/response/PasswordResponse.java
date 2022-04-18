package com.innova.pwValidator.response;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PasswordResponse {


    private List<String> message;


    public PasswordResponse(){
        message = new ArrayList<>();
    }
}
