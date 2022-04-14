package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.Validation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Scope("prototype")
@Component
public class PwValidationServiceImpl implements PwValidationService {

    private List<Validation> validationList = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(PwValidationServiceImpl.class);

    @Override
    public boolean isValid(String pw) {

        logger.info("validate password=>{} ", pw);

        for (Validation validation : validationList) {
            if (!validation.isValid(pw)) {
                logger.info("Password is invalid => {} ", pw);
                return false;
            }
        }
        return true;
    }

    @Override
    public PwValidationServiceImpl setUp(List<Class<? extends Validation>> validatorList) {
        if (validatorList == null || validatorList.size() == 0) {
            return this;
        }

        Set<Class<? extends Validation>> set = new HashSet<>(validatorList);
        for (Class<? extends Validation> v : set) {
            validationList.add()
        }
        return this;
    }

}
