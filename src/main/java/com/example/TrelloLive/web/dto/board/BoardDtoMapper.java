package com.example.TrelloLive.web.dto.board;

import com.example.TrelloLive.data.model.Board;

public interface BoardDtoMapper {
    ResponseBoardDto toResponseBoardDto(Board board);
    ResponseBoardsDto toResponseBoardsDto(Board board);
}
