package com.innova.pwValidator.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternValidation extends Validation{
    @Autowired
    private PwValidateProp pwValidateProp;

    private final Logger logger = LoggerFactory.getLogger(PatternValidation.class);

    @Override
    public boolean isValid(String pw) {
        return isValidType(pw) && isValidCount(pw, pwValidateProp.getMinCountMap());
    }

    private boolean isValidType(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        List<PatternType> types = pwValidateProp.getTypes();
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

    private Map<PatternType, Integer> getCount(String pw) {
        Map<PatternType, Integer> countMap = new HashMap<>();
        List<PatternType> types = pwValidateProp.getTypes();

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

    private boolean isValidCount(String pw, Map<PatternType, Integer> requiredMap) {
        if (isEmpty(pw)) {
            return false;
        }
        Map<PatternType, Integer> countMap = getCount(pw);

        for (Map.Entry<PatternType, Integer> entry : requiredMap.entrySet()) {
            PatternType requiredType = entry.getKey();
            if (!countMap.containsKey(requiredType)) {
                logger.info("password doesn't contain type =>{}", requiredType);
                return false;
            }
            int requiredCount = entry.getValue();
            if (countMap.get(requiredType) < requiredCount) {
                logger.info("password is found count =>{} which doesn't match to required count =>{} of type => {}",
                        countMap.get(requiredType), requiredCount, requiredType);
                return false;
            }
        }

        return true;
    }
}
