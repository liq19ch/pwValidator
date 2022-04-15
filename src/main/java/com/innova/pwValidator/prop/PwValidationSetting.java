package com.innova.pwValidator.prop;

import com.innova.pwValidator.prop.PatternType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@Component
@PropertySource("classpath:application.properties")
public class PwValidationSetting implements Setting {


    @Value("${pw.rule.length.min}")
    private int minLength;
    @Value("${pw.rule.length.max}")
    private int maxLength;
    @Value("#{'${pw.rule.type}'.split(',')}")
    private List<PatternType> types;
    @Value("#{${pw.rule.type.min.count}}")
    private Map<PatternType, Integer> minCountMap;
}
