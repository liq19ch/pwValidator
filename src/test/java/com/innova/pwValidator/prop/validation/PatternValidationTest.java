package com.innova.pwValidator.prop.validation;

import com.innova.pwValidator.prop.PatternType;
import com.innova.pwValidator.prop.PwValidationSetting;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.innova.pwValidator.prop.Def.MIN;
import static com.innova.pwValidator.prop.PatternType.LOWERCASE;
import static com.innova.pwValidator.prop.PatternType.NUMBER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(classes = PatternValidation.class)
class PatternValidationTest {

    @Mock
    private PatternValidation patternValidation;
    @Mock
    private PwValidationSetting setting;

    @BeforeEach
    void init() {
        setting = new PwValidationSetting();
        Map<PatternType, Integer> map = new HashMap<>();
        map.put(LOWERCASE, 1);
        map.put(NUMBER, 1);
        setting.setMinCountMap(map);
        setting.setTypes(Arrays.asList(NUMBER, LOWERCASE));
        patternValidation = new PatternValidation(setting);
    }

    @Test
    void isValid() {
        Assertions.assertFalse(patternValidation.isValid("abcde"));
        Assertions.assertFalse(patternValidation.isValid("12345"));
        Assertions.assertFalse(patternValidation.isValid("Adoe"));
        Assertions.assertFalse(patternValidation.isValid("/?_"));
        Assertions.assertFalse(patternValidation.isValid("aa"));
        Assertions.assertFalse(patternValidation.isValid("bbbb"));
        Assertions.assertFalse(patternValidation.isValid("?_v1"));
        Assertions.assertFalse(patternValidation.isValid("哈囉"));
        Assertions.assertFalse(patternValidation.isValid("11"));
        Assertions.assertTrue(patternValidation.isValid("a1234"));
        Assertions.assertTrue(patternValidation.isValid("a1"));
        Assertions.assertTrue(patternValidation.isValid("abcd1234"));
        Assertions.assertFalse(patternValidation.isValid("ADO"));
    }

    @Test
    @Disabled
    void getErrorMsg() {
    }


    @Test
    void isValidType() {
        Assertions.assertFalse(patternValidation.isValidType("哈囉"));
        Assertions.assertFalse(patternValidation.isValidType("/?_"));
        Assertions.assertFalse(patternValidation.isValidType("ADO"));
        Assertions.assertFalse(patternValidation.isValidType("Adoe"));
        Assertions.assertTrue(patternValidation.isValidType("abcd1234"));
        Assertions.assertTrue(patternValidation.isValidType("12345"));
        Assertions.assertTrue(patternValidation.isValidType("abcde"));
        Assertions.assertTrue(patternValidation.isValidType("a123bcd"));
    }

    private void testMap(Map<PatternType, Integer> expected, Map<PatternType, Integer> testCase, int size) {
        assertThat(testCase.entrySet(), equalTo(expected.entrySet()));
        //Test size
        assertThat(testCase.values(), hasSize(size));
    }

    @Test
    void getCount() {
        Map<PatternType, Integer> expected = new HashMap<>();
        expected.put(LOWERCASE, 4);
        expected.put(NUMBER, 3);
        Map<PatternType, Integer> testCase = patternValidation.getCount("ab12Acd1");
        testMap(expected, testCase, 2);
        assertThat(testCase, IsMapContaining.hasEntry(LOWERCASE, 4));
        assertThat(testCase, IsMapContaining.hasEntry(NUMBER, 3));

        expected = new HashMap<>();
        testCase = patternValidation.getCount("ZZZZZ");
        testMap(expected, testCase, 0);

        expected = new HashMap<>();
        expected.put(LOWERCASE, 6);
        expected.put(NUMBER, 4);
        testCase = patternValidation.getCount("acce1234,dp");
        testMap(expected, testCase, 2);
        assertThat(testCase, IsMapContaining.hasEntry(LOWERCASE, 6));
        assertThat(testCase, IsMapContaining.hasEntry(NUMBER, 4));

        expected = new HashMap<>();
        expected.put(NUMBER, 4);
        testCase = patternValidation.getCount("1111");
        testMap(expected, testCase, 1);
        assertThat(testCase, IsMapContaining.hasEntry(NUMBER, 4));

        expected = new HashMap<>();
        expected.put(LOWERCASE, 2);
        testCase = patternValidation.getCount("aa");
        testMap(expected, testCase, 1);
        assertThat(testCase, IsMapContaining.hasEntry(LOWERCASE, 2));

    }

    @Test
    void isValidCount() {

        Assertions.assertFalse(patternValidation.isValidCount("11", setting.getMinCountMap(), MIN));
        Assertions.assertFalse(patternValidation.isValidCount("aa", setting.getMinCountMap(), MIN));
        Assertions.assertFalse(patternValidation.isValidCount("ADER12", setting.getMinCountMap(), MIN));
        Assertions.assertFalse(patternValidation.isValidCount("bbbb", setting.getMinCountMap(), MIN));
        Assertions.assertTrue(patternValidation.isValidCount("a1", setting.getMinCountMap(), MIN));
        Assertions.assertTrue(patternValidation.isValidCount("a1234", setting.getMinCountMap(), MIN));
        Assertions.assertTrue(patternValidation.isValidCount("?_v1", setting.getMinCountMap(), MIN));

    }
}