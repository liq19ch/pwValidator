package com.innova.pwValidator.controller;

import com.innova.pwValidator.request.PasswordRequest;
import com.innova.pwValidator.response.PasswordResponse;
import com.innova.pwValidator.service.PwValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PasswordController {

    @Autowired
    private PwValidationService pwValidationService;

    @PostMapping("/pwValidate")
    public PasswordResponse validatePassword(@Valid @RequestBody PasswordRequest passwordReq) {
        return pwValidationService.valid(passwordReq.getPassword());
    }
}
