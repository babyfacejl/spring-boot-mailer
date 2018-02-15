package com.project.springbootmailer.utils;

public enum SendStatus {
    OK, ERROR;

    public static SendStatus of(int statusCode) {
        if (statusCode / 200 == 1) {
            return OK;
        }
        return ERROR;
    }
}
