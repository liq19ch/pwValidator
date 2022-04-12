package com.innova.pwValidator.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
public class PwValidateProp {


    @Value("${rule.length.min}")
    private int minLength;
    @Value("${rule.length.max}")
    private int maxLength;
    @Value("${rule.lowercase.count}")
    private int lowercaseCount;
    @Value("${rule.number.count}")
    private int numberCount;
    @Value("#{'${rule.type}'.split(',)}")
    private List<Type> types;
}
