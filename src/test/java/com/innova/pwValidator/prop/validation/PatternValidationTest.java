package com.innova.pwValidator.prop.validation;

import org.hamcrest.collection.IsMapContaining;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PatternValidation.class)
class PatternValidationTest {

    @Autowired
    private PatternValidation patternValidation;

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
    void getErrorMessage() {
        assertEquals(patternValidation.getErrorMessage(),"");
    }

    @Test
    void getSuccessMessage() {
        assertEquals(patternValidation.getSuccessMessage(),"PatternValidation is passed.");
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

    private void testMap(Map<String, Integer> expected, Map<String, Integer> testCase, int size) {
        assertThat(testCase.entrySet(), equalTo(expected.entrySet()));
        //Test size
        assertThat(testCase.values(), hasSize(size));
    }

    @Test
    void getCount() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("LOWERCASE", 4);
        expected.put("NUMBER", 3);
        Map<String, Integer> testCase = patternValidation.getCount("ab12Acd1");
        testMap(expected, testCase, 2);
        assertThat(testCase, IsMapContaining.hasEntry("LOWERCASE", 4));
        assertThat(testCase, IsMapContaining.hasEntry("NUMBER", 3));

        expected = new HashMap<>();
        testCase = patternValidation.getCount("ZZZZZ");
        testMap(expected, testCase, 0);

        expected = new HashMap<>();
        expected.put("LOWERCASE", 6);
        expected.put("NUMBER", 4);
        testCase = patternValidation.getCount("acce1234,dp");
        testMap(expected, testCase, 2);
        assertThat(testCase, IsMapContaining.hasEntry("LOWERCASE", 6));
        assertThat(testCase, IsMapContaining.hasEntry("NUMBER", 4));

        expected = new HashMap<>();
        expected.put("NUMBER", 4);
        testCase = patternValidation.getCount("1111");
        testMap(expected, testCase, 1);
        assertThat(testCase, IsMapContaining.hasEntry("NUMBER", 4));

        expected = new HashMap<>();
        expected.put("LOWERCASE", 2);
        testCase = patternValidation.getCount("aa");
        testMap(expected, testCase, 1);
        assertThat(testCase, IsMapContaining.hasEntry("LOWERCASE", 2));

    }

    @Test
    void isValidCount() {

        Assertions.assertFalse(patternValidation.isValidCount("11"));
        Assertions.assertFalse(patternValidation.isValidCount("aa"));
        Assertions.assertFalse(patternValidation.isValidCount("ADER12"));
        Assertions.assertFalse(patternValidation.isValidCount("bbbb"));
        Assertions.assertTrue(patternValidation.isValidCount("a1"));
        Assertions.assertTrue(patternValidation.isValidCount("a1234"));
        Assertions.assertTrue(patternValidation.isValidCount("?_v1"));

    }
}