package com.innova.pwValidator.prop.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class SequenceValidation extends Validation {

    private final Logger logger = LoggerFactory.getLogger(SequenceValidation.class);

    @Override
    public boolean isValid(String str) {
        if (isEmpty(str)) {
            return false;
        }
        if (isRepeat(str)) {
            logger.info("input is repeated");
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMsg() {
        return "input is repeated with sequence. ";
    }

    public boolean isRepeat(String str) {
        LinkedList<Character> linkedList = new LinkedList<>();
        int j = -1;
        int end = -1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (j == -1 && end == -1) {
                if (linkedList.contains(c)) {
                    j = linkedList.indexOf(c);
                    end = linkedList.size();
                    if (end - j  == 1) {
                        return true;
                    }
                    j++;
                }

                linkedList.add(c);
                continue;
            }
            if (c == linkedList.get(j)) {
                j++;
                if (j == end) {
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
}
