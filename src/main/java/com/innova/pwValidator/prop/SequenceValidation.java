package com.innova.pwValidator.prop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

public class SequenceValidation extends Validation{

    private final Logger logger = LoggerFactory.getLogger(SequenceValidation.class);

    @Override
    public boolean isValid(String pw) {
        if (isEmpty(pw)) {
            return false;
        }
        if (isRepeat(pw)) {
            logger.info("password is repeated");
            setErrorMsg("password is repeated with sequence. ");
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
