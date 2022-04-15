package com.innova.pwValidator.prop;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.innova.pwValidator.prop.PatternType.LOWERCASE;
import static com.innova.pwValidator.prop.PatternType.NUMBER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PwValidationSetting.class)
class PwValidationSettingTest {

    @Autowired
    private PwValidationSetting pwValidationSetting;

    @Test
    public void getProperties() {

        Assertions.assertEquals(5, pwValidationSetting.getMinLength());
        Assertions.assertEquals(12, pwValidationSetting.getMaxLength());
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(PatternType.LOWERCASE, PatternType.NUMBER)), pwValidationSetting.getTypes());
        Map<PatternType, Integer> expected = new HashMap<>();
        expected.put(LOWERCASE, 1);
        expected.put(NUMBER, 1);
        assertThat(pwValidationSetting.getMinCountMap().entrySet(), equalTo(expected.entrySet()));
        assertThat(pwValidationSetting.getMinCountMap().values(), hasSize(2));
        assertThat(pwValidationSetting.getMinCountMap(), IsMapContaining.hasEntry(LOWERCASE, 1));
        assertThat(pwValidationSetting.getMinCountMap(), IsMapContaining.hasEntry(NUMBER, 1));
    }
}