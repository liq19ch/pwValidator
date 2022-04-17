package com.innova.pwValidator.prop.validation;

import com.innova.pwValidator.prop.PatternType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.innova.pwValidator.prop.Def;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.innova.pwValidator.prop.Def.MAX;
import static com.innova.pwValidator.prop.Def.MIN;

@Component
@ComponentScan("com.innova")
public class PatternValidation extends Validation {


    private final Logger logger = LoggerFactory.getLogger(PatternValidation.class);

    private String errorMsg = "";

    private Map<PatternType, Integer> minCountMap;

    private List<PatternType> typeList;

    public PatternValidation(PwValidationSetting setting) {
        this.minCountMap = setting.getMinCountMap();
        this.typeList = setting.getTypes();
    }

    @Override
    public boolean isValid(String str) {
        if (!isValidType(str)) {
            errorMsg = "input doesn't match to type. ";
            return false;
        }

        return isValidCount(str, minCountMap, MIN);
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean isValidType(String str) {
        if (isEmpty(str)) {
            return false;
        }
        List<PatternType> types = typeList;
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (PatternType type : types) {
            sb.append(type.getPattern());
        }
        sb.append("]+");
        if (!str.matches(sb.toString())) {
            logger.info("input doesn't match to type");
            return false;
        }
        return true;
    }

    public Map<PatternType, Integer> getCount(String str) {
        Map<PatternType, Integer> countMap = new HashMap<>();
        List<PatternType> types = typeList;

        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            for (PatternType type : types) {
                String pattern = "[" + type.getPattern() + "]+";
                if (!s.matches(pattern)) {
                    continue;
                }
                countMap.put(type, countMap.getOrDefault(type, 0) + 1);
                break;
            }
        }
        return countMap;
    }


    public boolean isValidCount(String str, Map<PatternType, Integer> requiredMap, Def type) {
        if (isEmpty(str)) {
            return false;
        }
        Map<PatternType, Integer> countMap = getCount(str);

        for (Map.Entry<PatternType, Integer> entry : requiredMap.entrySet()) {
            PatternType requiredType = entry.getKey();
            if (!countMap.containsKey(requiredType)) {
                logger.info("input doesn't contain type =>{}", requiredType);
                errorMsg = "input doesn't contain specific type. ";
                return false;
            }
            int requiredCount = entry.getValue();
            if (type.equals(MIN)) {
                if (countMap.get(requiredType) < requiredCount) {
                    logger.info("input is only found count =>{} which doesn't match to min required count =>{} of type => {}",
                            countMap.get(requiredType), requiredCount, requiredType);
                    errorMsg = "input doesn't meet the count of types. ";
                    return false;
                }
            } else if (type.equals(MAX)) {
                if (countMap.get(requiredType) > requiredCount) {
                    logger.info("input is found count =>{} which doesn't match to max required count =>{} of type => {}",
                            countMap.get(requiredType), requiredCount, requiredType);
                    errorMsg = "input is over the the count of types. ";
                    return false;
                }
            }
        }

        return true;
    }
}
