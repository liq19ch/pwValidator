package com.innova.pwValidator.prop.validation;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@ComponentScan("com.innova")
@PropertySource("classpath:application.properties")
public class PatternValidation implements Validation {


    private final Logger logger = LoggerFactory.getLogger(PatternValidation.class);

    private String errorMsg = "";


    @Value("#{${pw.type.min.count}}")
    private Map<String, Integer> minCountMap;

    @Value("#{'${pw.type}'.split(',')}")
    private List<String> typeList;


    @Override
    public boolean isValid(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (!isValidType(str)) {
            errorMsg = "input doesn't match to type.";
            return false;
        }

        return isValidCount(str);
    }

    @Override
    public String getErrorMessage() {
        return errorMsg;
    }
    @Override
    public String getSuccessMessage() {
        return "PatternValidation is passed.";
    }

    private String getType(String str) {
        switch (str) {
            case "LOWERCASE":
                return "a-z";
            case "NUMBER":
                return "0-9";
            default:
                return "";
        }
    }

    public boolean isValidType(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (String type : typeList) {
            sb.append(getType(type));
        }

        sb.append("]+");
        if (!str.matches(sb.toString())) {
            logger.info("input doesn't match to type");
            return false;
        }
        return true;
    }

    public Map<String, Integer> getCount(String str) {
        Map<String, Integer> countMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            for (String type : typeList) {

                String pattern = "[" + getType(type) + "]";
                if (!s.matches(pattern)) {
                    continue;
                }
                countMap.put(type, countMap.getOrDefault(type, 0) + 1);
                break;
            }
        }
        return countMap;
    }


    public boolean isValidCount(String str) {
        Map<String, Integer> countMap = getCount(str);

        for (Map.Entry<String, Integer> entry : minCountMap.entrySet()) {
            String requiredType = entry.getKey();
            if (!countMap.containsKey(requiredType)) {
                logger.info("input doesn't contain type =>{}", requiredType);
                errorMsg = "input doesn't contain specific type.";
                return false;
            }
            int requiredCount = entry.getValue();
            if (countMap.get(requiredType) < requiredCount) {
                logger.info("input is only found count =>{} which doesn't match to min required count =>{} of type => {}",
                        countMap.get(requiredType), requiredCount, requiredType);
                errorMsg = "input doesn't meet the count of types.";
                return false;
            }
        }
        return true;
    }
}
