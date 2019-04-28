package com.pantkiewicz.demo.picturemanager.user.service;

public enum LoginStatus {

    SUCCESS("Login successful"),
    USER_NOT_FOUND("User not found"),
    INCORRECT_PASSWORD("Incorrect password");

    private final String message;

    LoginStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
