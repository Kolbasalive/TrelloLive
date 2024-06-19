package com.example.TrelloLive.web.dto.board;

import com.example.TrelloLive.data.model.Board;
import com.example.TrelloLive.web.dto.task.TaskDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardDtoMapperImpl implements BoardDtoMapper {
    private final TaskDtoMapper taskDtoMapper;

    @Override
    public ResponseBoardDto toResponseBoardDto(Board board) {
        ResponseBoardDto responseBoardDto = new ResponseBoardDto();
        responseBoardDto.setBoardId(board.getBoardId())
                .setTitle(board.getName());
        List<TaskForBoardDto> taskDto = new ArrayList<>(board.getTasks()
                .stream().map(taskDtoMapper::toTaskForBoardDto).toList());
        responseBoardDto.setTaskList(taskDto);

        return responseBoardDto;
    }

    @Override
    public ResponseBoardsDto toResponseBoardsDto(Board board) {
        ResponseBoardsDto boardsDto = new ResponseBoardsDto();
        boardsDto.setBoardId(board.getBoardId())
                .setTitle(board.getName());

        return boardsDto;
    }


}
