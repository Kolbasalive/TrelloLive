package com.example.TrelloLive.exception;

import static java.lang.String.format;

public class TagNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Tag not found by tagId: %s";

    public TagNotFoundException(String tagId) {
        super(format(MESSAGE, tagId));
    }
}
