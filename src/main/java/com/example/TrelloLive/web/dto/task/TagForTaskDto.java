package com.example.TrelloLive.web.dto.task;

import lombok.Data;

import java.util.UUID;

@Data
public class TagForTaskDto {
    private UUID tagId;
    private String name;
}
