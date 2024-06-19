package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.web.dto.task.ResponseTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;

import java.util.List;

public interface TaskService {

    Task postTask(TaskDto taskDto);

    ResponseTaskDto getByTLId(String tLId);

    String changeStatus(String status, String tlId);

    List<ResponseUserDto> getAssigneesInTask(String tlId);

    Task addAssigneesInTask(String userId, String tlId);

    Task createOrSetTagInTask(String tagId, String tlId);

    Task deleteAssigneesInTask(String userId, String tlId);

    Task deleteTagInTask(String tagId, String tlId);

    List<ResponseTaskDto> getTaskByTagId(String tagId);

    Task removeTask(String tlId);
}
