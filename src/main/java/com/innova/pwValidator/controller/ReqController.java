package com.innova.pwValidator.controller;

import com.innova.pwValidator.req.PasswordReq;
import com.innova.pwValidator.service.PwValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReqController {

    @Autowired
    private ApplicationContext context;

    @PostMapping("/pwValidate")
    public String register(@Valid @RequestBody PasswordReq passwordReq) {
        PwValidationService pwValidationService = context.getBean(PwValidationService.class);
        return pwValidationService.isValid(passwordReq.getPassword()) ? "success" : "fail";
    }
}
