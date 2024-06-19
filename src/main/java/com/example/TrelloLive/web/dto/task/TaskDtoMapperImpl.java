package com.example.TrelloLive.web.dto.task;

import com.example.TrelloLive.data.model.Board;
import com.example.TrelloLive.data.model.Status;
import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.web.dto.board.BoardForTaskDto;
import com.example.TrelloLive.web.dto.board.TaskForBoardDto;
import com.example.TrelloLive.web.dto.tag.TagDtoMapper;
import com.example.TrelloLive.web.dto.user.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDtoMapperImpl implements TaskDtoMapper {
    private final TagDtoMapper tagDtoMapper;
    private final UserDtoMapper userDtoMapper;

    @Override
    public Task toTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle())
                .setDescription(taskDto.getDescription())
                .setCreationDate(taskDto.getCreationDate())
                .setStatus(Status.valueOf(taskDto.getStatus()))
                .setPriority((taskDto.getPriority()))
                .setDeadline(taskDto.getDeadline())
                .setSprint(taskDto.getSprint())
                .setTlId(taskDto.getTlId())
                .setTags(taskDto.getTags()
                        .stream().map(tagDtoMapper::toTag).toList())
                .setAssignees(taskDto.getAssignees()
                        .stream().map(userDtoMapper::toUsers).toList())
                .setBoard(toBoard(taskDto.getBoard()));
        return task;
    }

    @Override
    public ResponseTaskDto toGetTaskDto(Task task) {
        ResponseTaskDto responseTaskDto = new ResponseTaskDto();
        responseTaskDto.setTitle(task.getTitle())
                .setDescription(task.getDescription())
                .setCreationDate(task.getCreationDate())
                .setStatus(String.valueOf(task.getStatus()))
                .setPriority((task.getPriority()))
                .setDeadline(task.getDeadline())
                .setSprint(task.getSprint())
                .setTlId(task.getTlId())
                .setTags(task.getTags()
                        .stream().map(tagDtoMapper::toTagForTaskDto).toList())
                .setAssignees(task.getAssignees()
                        .stream().map(userDtoMapper::toUserForTaskDto).toList())
                .setBoard(toBoardToTaskDto(task.getBoard()));

        return responseTaskDto;
    }

    @Override
    public TaskForBoardDto toTaskForBoardDto(Task task) {
        TaskForBoardDto taskForBoardDto = new TaskForBoardDto();
        taskForBoardDto.setTitle(task.getTitle())
                .setDescription(task.getDescription())
                .setCreationDate(task.getCreationDate())
                .setStatus(String.valueOf(task.getStatus()))
                .setPriority((task.getPriority()))
                .setDeadline(task.getDeadline())
                .setSprint(task.getSprint())
                .setTlId(task.getTlId())
                .setTags(task.getTags()
                        .stream().map(tagDtoMapper::toTagForTaskDto).toList())
                .setAssignees(task.getAssignees()
                        .stream().map(userDtoMapper::toUserForTaskDto).toList());

        return taskForBoardDto;
    }


    private Board toBoard(BoardForTaskDto boardForTaskDto) {
        Board board = new Board();
        board.setBoardId(boardForTaskDto.getBoardId());
        board.setName(boardForTaskDto.getName());

        return board;
    }


    private BoardForTaskDto toBoardToTaskDto(Board board) {
        BoardForTaskDto boardForTaskDto = new BoardForTaskDto();
        boardForTaskDto.setBoardId(board.getBoardId());
        boardForTaskDto.setName(board.getName());

        return boardForTaskDto;
    }
}
