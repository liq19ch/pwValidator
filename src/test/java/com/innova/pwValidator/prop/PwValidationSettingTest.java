package com.innova.pwValidator.prop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

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

    }
}