package com.example.TrelloLive.exception;

import static java.lang.String.format;

public class TaskNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Task not found by tLId: %s";

    public TaskNotFoundException(String tLId) {
        super(format(MESSAGE, tLId));
    }
}
