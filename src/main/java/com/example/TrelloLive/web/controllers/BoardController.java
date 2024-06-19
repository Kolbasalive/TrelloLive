package com.example.TrelloLive.web.controllers;

import com.example.TrelloLive.service.BoardService;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.board.ResponseBoardDto;
import com.example.TrelloLive.web.dto.board.ResponseBoardsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trello/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("{boardId}")
    public ResponseBoardDto getBoards(@PathVariable String boardId) {
        return boardService.getBoardsById(boardId);
    }

    @GetMapping()
    public List<ResponseBoardsDto> getBoards() {
        return boardService.getBoards();
    }

    @DeleteMapping("{boardId}")
    public ResponseDto deleteBoardById(@PathVariable String boardId) {
        return new ResponseDto(boardService.deleteBoard(boardId).getBoardId().toString());
    }
}