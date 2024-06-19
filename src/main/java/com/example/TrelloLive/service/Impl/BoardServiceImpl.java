package com.example.TrelloLive.service.Impl;

import com.example.TrelloLive.data.model.Board;
import com.example.TrelloLive.data.repo.BoardRepository;
import com.example.TrelloLive.data.repo.TaskRepository;
import com.example.TrelloLive.exception.BoardNotFoundException;
import com.example.TrelloLive.service.BoardService;
import com.example.TrelloLive.web.dto.board.BoardDtoMapper;
import com.example.TrelloLive.web.dto.board.ResponseBoardDto;
import com.example.TrelloLive.web.dto.board.ResponseBoardsDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardDtoMapper boardDtoMapper;
    private final TaskRepository taskRepository;
    private final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Override
    public ResponseBoardDto getBoardsById(String boardId) {
        Board board = boardRepository.findById(UUID.fromString(boardId))
                .orElseThrow(() -> new BoardNotFoundException(boardId));

        return boardDtoMapper.toResponseBoardDto(board);
    }

    @Override
    public List<ResponseBoardsDto> getBoards() {
        List<Board> boardsList = boardRepository.findAll();
        return boardsList.stream().map(boardDtoMapper::toResponseBoardsDto).toList();
    }

    @Override
    public Board deleteBoard(String boardId) {
        Board board = boardRepository.findById(UUID.fromString(boardId))
                .orElseThrow(() -> new BoardNotFoundException(boardId));
        boardRepository.delete(board);
        taskRepository.deleteAll(board.getTasks());
        logger.info("Delete board by boardId: " + board.getBoardId());

        return board;
    }
}
