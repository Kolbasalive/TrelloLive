package com.example.TrelloLive.web.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class ResponseBoardsDto {
    private UUID boardId;
    private String title;
}
