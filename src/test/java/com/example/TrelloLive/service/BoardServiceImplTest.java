package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Board;
import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.data.repo.BoardRepository;
import com.example.TrelloLive.data.repo.TaskRepository;
import com.example.TrelloLive.exception.BoardNotFoundException;
import com.example.TrelloLive.service.Impl.BoardServiceImpl;
import com.example.TrelloLive.web.dto.board.BoardDtoMapper;
import com.example.TrelloLive.web.dto.board.ResponseBoardDto;
import com.example.TrelloLive.web.dto.board.ResponseBoardsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceImplTest {
    @InjectMocks
    private BoardServiceImpl boardService;
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private BoardDtoMapper boardDtoMapper;
    @Mock
    private TaskRepository taskRepository;
    private Board board;
    private UUID boardId;
    private ResponseBoardDto responseBoardDto;

    @BeforeEach
    public void setUp() {
        boardId = UUID.randomUUID();
        board = new Board();
        board.setBoardId(boardId)
                .setName("Board A");
        responseBoardDto = new ResponseBoardDto();
        responseBoardDto.setBoardId(boardId)
                .setTitle("Something");
    }

    @Test
    void getBoardsById_ReturnBoardResponseBoardDto() {
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
        when(boardDtoMapper.toResponseBoardDto(board)).thenReturn(responseBoardDto);
        ResponseBoardDto result = boardService.getBoardsById(boardId.toString());

        assertEquals(result.getBoardId(), responseBoardDto.getBoardId());
    }

    @Test
    void getBoardsById_ReturnBoardNotFoundException() {
        boardId = UUID.randomUUID();
        when(boardRepository.findById(boardId)).thenReturn(Optional.empty());

        assertThrows(BoardNotFoundException.class,
                () -> boardService.getBoardsById(boardId.toString()));
    }

    @Test
    void getBoards_ReturnListResponseBoardsDto() {
        Board board1 = new Board();
        ResponseBoardsDto boardsDto = new ResponseBoardsDto();
        ResponseBoardsDto boardsDto1 = new ResponseBoardsDto();
        board1.setBoardId(UUID.randomUUID())
                .setName("Board B");
        boardsDto.setBoardId(boardId)
                .setTitle("Board A");
        boardsDto1.setBoardId(board1.getBoardId())
                .setTitle("Board A");
        List<Board> boardList = List.of(board, board1);
        when(boardRepository.findAll()).thenReturn(boardList);
        when(boardDtoMapper.toResponseBoardsDto(board)).thenReturn(boardsDto);
        when(boardDtoMapper.toResponseBoardsDto(board1)).thenReturn(boardsDto1);
        List<ResponseBoardsDto> result = boardService.getBoards();

        assertEquals(result.get(0).getBoardId(), boardsDto.getBoardId());
        assertEquals(result.get(1).getBoardId(), boardsDto1.getBoardId());
    }

    @Test
    void deleteBoard_ReturnBoard() {
        Task task = new Task();
        Task task1 = new Task();
        board.setTasks(List.of(task, task1));
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
        Board result = boardService.deleteBoard(boardId.toString());

        assertEquals(boardId, result.getBoardId());
        verify(boardRepository, times(1)).findById(boardId);
        verify(boardRepository, times(1)).delete(board);
        verify(taskRepository, times(1)).deleteAll(board.getTasks());
    }

    @Test
    void deleteBoard_ReturnBoardNotFoundException() {
        boardId = UUID.randomUUID();
        when(boardRepository.findById(boardId)).thenReturn(Optional.empty());
        assertThrows(BoardNotFoundException.class,
                () -> boardService.deleteBoard(boardId.toString()));
    }

}
