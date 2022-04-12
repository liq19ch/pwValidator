package com.innova.pwValidator.service;

public enum Type {

    PATTERN_LOWERCASE("^[a-z]$"),
    PATTERN_NUMBER("^[0-9]$");

    private String pattern;

    Type(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
