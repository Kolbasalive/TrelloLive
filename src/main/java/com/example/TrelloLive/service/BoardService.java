package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Board;
import com.example.TrelloLive.web.dto.board.ResponseBoardDto;
import com.example.TrelloLive.web.dto.board.ResponseBoardsDto;

import java.util.List;

public interface BoardService {
    ResponseBoardDto getBoardsById(String boardId);

    List<ResponseBoardsDto> getBoards();

    Board deleteBoard(String boardId);
}
