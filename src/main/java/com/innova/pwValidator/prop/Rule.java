package com.innova.pwValidator.prop;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

@Component
public class Rule {

    @Autowired
    private PwValidateProp pwValidateProp;

    private final Logger logger = LoggerFactory.getLogger(Rule.class);

    private boolean isEmpty(String pw) {
        if (pw == null || pw.length() < 1) {
            logger.info("password is empty");
            return true;
        }
        return false;
    }

    private boolean isValidLength(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        int min = pwValidateProp.getMinLength();
        int max = pwValidateProp.getMaxLength();
        if (min > max) {
            // exc
        }
        if (pw.length() < min || pw.length() > max) {
            return false;
        }
        return true;
    }

    private boolean isValidType(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        List<Type> types = pwValidateProp.getTypes();
        for (Type type : types) {
            if (!pw.matches(type.getPattern())) {
                logger.info("password doesn't match to =>{}", type);
                return false;
            }
        }
        return true;
    }

    private boolean isValidCount(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        Map<Type, Integer> requiredCountMap = pwValidateProp.getCountMap();
        List<Type> types = pwValidateProp.getTypes();
        Map<Type, Integer> countMap = new HashMap<>();

        for (int i = 0; i < pw.length(); i++) {
            String s = String.valueOf(pw.charAt(i));
            for (Type type : types) {
                if (!s.matches(type.getPattern())) {
                    continue;
                }
                countMap.put(type, countMap.getOrDefault(type, 0) + 1);
                break;
            }
        }

        for (Map.Entry<Type, Integer> entry : requiredCountMap.entrySet()) {
            Type requiredType = entry.getKey();
            if (!countMap.containsKey(requiredType)) {
                logger.info("password doesn't contain type =>{}", requiredType);
                return false;
            }
            int requiredCount = entry.getValue();
            if (countMap.get(requiredType) < requiredCount) {
                logger.info("password is only found count =>{} which doesn't match to required count =>{} of type => {}", countMap.get(requiredType), requiredCount, requiredType);
                return false;
            }
        }

        return true;
    }

    private boolean isValidSequence(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        if (isRepeat(pw)) {
            logger.info("password is repeated");
            return false;
        }
        return true;
    }

    private boolean isRepeat(String pw) {
        LinkedList<Character> linkedList = new LinkedList<>();
        int j = -1;
        int end = -1;
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            if (j == -1 && end == -1 && linkedList.contains(c)) {
                j = linkedList.indexOf(c);
                end = linkedList.size() - j;
                j++;
                linkedList.add(c);
                continue;
            }
            if (c == linkedList.get(j)) {
                j++;
                if (j > end) {
                    return true;
                }
            } else {
                j = -1;
                end = -1;
            }
            linkedList.add(c);
        }

        return false;
    }


    public boolean isValidate(String pw) {
        return isValidLength(pw) && isValidSequence(pw) && isValidType(pw) && isValidCount(pw);
    }
}


