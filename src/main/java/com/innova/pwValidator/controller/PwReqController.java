package com.innova.pwValidator.controller;

import com.innova.pwValidator.prop.validation.*;
import com.innova.pwValidator.req.PasswordReq;
import com.innova.pwValidator.service.PwValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class PwReqController {

    @Autowired
    private PwValidationService pwValidationService;

    @PostMapping("/pwValidate")
    public String validatePassword(@Valid @RequestBody PasswordReq passwordReq) {
        String errorMessage = pwValidationService.valid(passwordReq.getPassword());
        if (errorMessage == null || errorMessage.equals("")) {
           return  "success";
        }
        return errorMessage;
    }
}
