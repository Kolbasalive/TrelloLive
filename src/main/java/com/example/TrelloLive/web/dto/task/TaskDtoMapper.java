package com.example.TrelloLive.web.dto.task;

import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.web.dto.board.TaskForBoardDto;

public interface TaskDtoMapper {

    Task toTask(TaskDto taskDto);

    ResponseTaskDto toGetTaskDto(Task task);

    TaskForBoardDto toTaskForBoardDto(Task task);
}
