package com.innova.pwValidator.prop;

public enum PatternType {

    LOWERCASE("a-z"),
    NUMBER("0-9");

    private String pattern;

    PatternType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }



}
