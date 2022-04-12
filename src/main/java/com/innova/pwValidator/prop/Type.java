package com.innova.pwValidator.prop;

public enum Type {

    LOWERCASE("^[a-z]$"),
    NUMBER("^[0-9]$");

    private String pattern;

    Type(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
