package com.example.TrelloLive.web.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
public class ResponseBoardDto {
    private UUID boardId;
    private String title;
    private List<TaskForBoardDto> taskList;
}