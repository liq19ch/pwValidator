package com.innova.pwValidator.prop.validation;

import com.innova.pwValidator.prop.PatternType;
import com.innova.pwValidator.prop.PwValidationSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.innova.pwValidator.prop.Def;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.innova.pwValidator.prop.Def.MAX;
import static com.innova.pwValidator.prop.Def.MIN;

@Component
public class PatternValidation extends Validation {
    @Autowired
    private PwValidationSetting pwValidationSetting;

    private final Logger logger = LoggerFactory.getLogger(PatternValidation.class);

    private String errorMsg = "";

    @Override
    public boolean isValid(String pw) {
        if (!isValidType(pw)) {
            errorMsg = "password doesn't match to type. ";
            return false;
        }

        return !isValidCount(pw, pwValidationSetting.getMinCountMap(), MIN);
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean isValidType(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        List<PatternType> types = pwValidationSetting.getTypes();
        StringBuilder sb = new StringBuilder();
        for (PatternType type : types) {
            if (sb.length() != 0) {
                sb.append("|");
            }
            sb.append(type.getPattern());
        }
        if (!pw.matches(sb.toString())) {
            logger.info("password doesn't match to type");
            return false;
        }
        return true;
    }

    public Map<PatternType, Integer> getCount(String pw) {
        Map<PatternType, Integer> countMap = new HashMap<>();
        List<PatternType> types = pwValidationSetting.getTypes();

        for (int i = 0; i < pw.length(); i++) {
            String s = String.valueOf(pw.charAt(i));
            for (PatternType type : types) {
                if (!s.matches(type.getPattern())) {
                    continue;
                }
                countMap.put(type, countMap.getOrDefault(type, 0) + 1);
                break;
            }
        }
        return countMap;
    }


    public boolean isValidCount(String pw, Map<PatternType, Integer> requiredMap, Def type) {
        if (isEmpty(pw)) {
            return false;
        }
        Map<PatternType, Integer> countMap = getCount(pw);

        for (Map.Entry<PatternType, Integer> entry : requiredMap.entrySet()) {
            PatternType requiredType = entry.getKey();
            if (!countMap.containsKey(requiredType)) {
                logger.info("password doesn't contain type =>{}", requiredType);
                errorMsg = "password doesn't contain specific type. ";
                return false;
            }
            int requiredCount = entry.getValue();
            if (type.equals(MIN)) {
                if (countMap.get(requiredType) < requiredCount) {
                    logger.info("password is only found count =>{} which doesn't match to min required count =>{} of type => {}",
                            countMap.get(requiredType), requiredCount, requiredType);
                    errorMsg = "password doesn't meet the count of types. ";
                    return false;
                }
            } else if (type.equals(MAX)) {
                if (countMap.get(requiredType) > requiredCount) {
                    logger.info("password is found count =>{} which doesn't match to max required count =>{} of type => {}",
                            countMap.get(requiredType), requiredCount, requiredType);
                    errorMsg = "password is over the the count of types. ";
                    return false;
                }
            }
        }

        return true;
    }
}
