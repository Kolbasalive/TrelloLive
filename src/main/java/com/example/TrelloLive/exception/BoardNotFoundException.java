package com.example.TrelloLive.exception;

import static java.lang.String.format;

public class BoardNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Board not found by boardId: %s";

    public BoardNotFoundException(String boardId) {
        super(format(MESSAGE, boardId));
    }
}
