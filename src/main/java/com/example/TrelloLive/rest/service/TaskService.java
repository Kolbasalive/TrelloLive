package com.example.TrelloLive.rest.service;

import com.example.TrelloLive.rest.dto.TaskDto;

public interface TaskService {
    String postTask(TaskDto taskDto);

}
