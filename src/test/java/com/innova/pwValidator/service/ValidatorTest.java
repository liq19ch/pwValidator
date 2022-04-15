package com.innova.pwValidator.service;

import com.innova.pwValidator.prop.validation.*;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.List;

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

    private List<Validation> validationList = new ArrayList<>();

    public void addValidation(Validation v) {
        this.validationList.add(v);
    }

    public String validate(String pw) {
        for (Validation validation : validationList) {
            if (!validation.isValid(pw)) {
                return validation.getErrorMsg();
            }
        }
        return null;
    }

    @BeforeEach
    void init() {
        validationList.add(new EmptyValidation());
        validationList.add(new LengthValidation());
        validationList.add(new PatternValidation());
        validationList.add(new SequenceValidation());
    }

    @Test
    void addValidation() {
        validator.addValidation(new EmptyValidation());
        validator.addValidation(new LengthValidation());
        validator.addValidation(new PatternValidation());
        validator.addValidation(new SequenceValidation());

        assertThat(validator.getValidationList(), hasSize(4));
        assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }

    @Test
    void validate() {
    }
}