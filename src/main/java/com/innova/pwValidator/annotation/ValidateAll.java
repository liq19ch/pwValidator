package com.innova.pwValidator.annotation;


import com.innova.pwValidator.service.VerifyRegexConst;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidateAll {
    String pattern() default "";

    String msg() default "format is invalid";

    boolean notNull() default true;

    Class<?> enumClass() default String.class;
}
