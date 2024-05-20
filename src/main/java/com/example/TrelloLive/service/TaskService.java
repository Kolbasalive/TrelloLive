package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.web.dto.task.GetTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;

import java.util.UUID;

public interface TaskService {
    /**
    * @param taskDto onhobhobobo
    */
    Task postTask(TaskDto taskDto);

    GetTaskDto getByTLId(String tLId);
}
