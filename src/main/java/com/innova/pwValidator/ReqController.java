package com.innova.pwValidator;

import com.innova.pwValidator.req.PasswordReq;
import com.innova.pwValidator.service.PwValidationService;
import com.innova.pwValidator.service.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
public class ReqController {

    @Autowired
    private ApplicationContext context;

    @PostMapping("/pwValidate")
    public String register(@Valid @RequestBody PasswordReq passwordReq) {
        Rule rule = new Rule();
        if(rule.isValidate(passwordReq.getPassword())){
            return "success";
        }
        return "fail";
    }
}
