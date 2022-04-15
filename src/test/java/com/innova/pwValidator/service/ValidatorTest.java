package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.PatternType;
import com.innova.pwValidator.prop.PwValidationSetting;
import com.innova.pwValidator.prop.validation.*;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.util.Length;

import java.util.*;

import static com.innova.pwValidator.prop.PatternType.LOWERCASE;
import static com.innova.pwValidator.prop.PatternType.NUMBER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static sun.nio.cs.Surrogate.is;

@SpringBootTest(classes = Validator.class)
@RunWith(MockitoJUnitRunner.class)
class ValidatorTest {

    @Autowired
    private Validator validator;
    @Mock
    private PwValidationSetting setting;

    @BeforeEach
    void init() {
        setting = new PwValidationSetting();
        setting.setMinLength(5);
        setting.setMaxLength(12);
        Map<PatternType, Integer> map = new HashMap<>();
        map.put(LOWERCASE, 1);
        map.put(NUMBER, 1);
        setting.setMinCountMap(map);
        List<PatternType> typeList = Arrays.asList(NUMBER, LOWERCASE);
        setting.setTypes(typeList);
    }

    @Test
    void addValidation() {
        validator.addValidation(new EmptyValidation());
        validator.addValidation(new LengthValidation(setting));
        validator.addValidation(new PatternValidation(setting));
        validator.addValidation(new SequenceValidation());

        assertThat(validator.getValidationList(), hasSize(4));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }

    @Test
    void validate() {
        List<String> list = new ArrayList<>(Arrays.asList("", null, "abc", "123123abc123abc", "AZ123A?", "c__189", "cccc", "11234abc"));
        validator.addValidation(new EmptyValidation());
        validator.addValidation(new LengthValidation(setting));
        validator.addValidation(new PatternValidation(setting));
        validator.addValidation(new SequenceValidation());

        for (String pw : list) {
            assertFalse(validator.validate(pw).isEmpty());
        }

    }
}