package com.example.TrelloLive.rest.service.Impl;

import com.example.TrelloLive.dao.repo.TaskRepository;
import com.example.TrelloLive.rest.dto.TaskDto;
import com.example.TrelloLive.rest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    @Override
    public String postTask(TaskDto taskDto) {
        //taskRepository.save();
        return null;
    }
}
