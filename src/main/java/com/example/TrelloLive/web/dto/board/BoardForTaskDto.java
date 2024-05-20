package com.example.TrelloLive.web.dto.board;

import lombok.Data;

import java.util.UUID;

@Data
public class BoardForTaskDto {
    private UUID boardId;
    private String name;
}


