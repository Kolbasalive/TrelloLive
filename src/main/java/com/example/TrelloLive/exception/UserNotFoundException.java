package com.example.TrelloLive.exception;

import static java.lang.String.format;

public class UserNotFoundException extends RuntimeException{
    private static final String MESSAGE = "User not found by userId: %s";

    public UserNotFoundException(String userId) {
        super(format(MESSAGE, userId));
    }

}
