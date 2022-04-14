package com.innova.pwValidator.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@Component
public class PwValidateProp {


    @Value("${rule.length.min}")
    private int minLength;
    @Value("${rule.length.max}")
    private int maxLength;
    @Value("#{'${rule.type}'.split(',)}")
    private List<PatternType> types;
    @Value("${rule.type.min.count}")
    private Map<PatternType, Integer> minCountMap;
}
