package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Board;
import com.example.TrelloLive.data.repo.BoardRepository;
import com.example.TrelloLive.service.Impl.BoardServiceImpl;
import com.example.TrelloLive.web.dto.board.ResponseBoardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardRepository boardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBoardsById_BoardExists(){

    }

}
