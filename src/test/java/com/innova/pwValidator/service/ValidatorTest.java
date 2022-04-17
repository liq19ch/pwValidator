package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.PatternType;
import com.innova.pwValidator.prop.validation.*;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static com.innova.pwValidator.prop.PatternType.LOWERCASE;
import static com.innova.pwValidator.prop.PatternType.NUMBER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Validator.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        setting.setTypes( Arrays.asList(NUMBER, LOWERCASE));
    }

    @Test
    @Order(1)
    void addValidation() {
        List<Validation> list = Arrays.asList(new EmptyValidation(),new LengthValidation(setting),
                new PatternValidation(setting),new SequenceValidation());
        validator.addValidation(list);
        assertThat(validator.getValidationList(), hasSize(4));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }

    @Test
    @Order(2)
    void validate() {
        List<String> list = new ArrayList<>(Arrays.asList("", null, "abc", "123123abc123abc", "AZ123A?", "c__189", "cccc", "11234abc"));

        for (String pw : list) {
            assertFalse(validator.validate(pw).isEmpty());
        }

    }
}