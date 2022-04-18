package com.innova.pwValidator.service;


import com.innova.pwValidator.response.PasswordResponse;

public interface PwValidationService {
    PasswordResponse valid(String pw);
}