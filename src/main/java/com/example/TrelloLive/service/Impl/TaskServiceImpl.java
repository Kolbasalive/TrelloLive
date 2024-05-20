package com.example.TrelloLive.service.Impl;

import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.data.repo.TaskRepository;
import com.example.TrelloLive.exception.TaskNotFoundException;
import com.example.TrelloLive.web.dto.task.GetTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;
import com.example.TrelloLive.web.dto.task.TaskDtoMapper;
import com.example.TrelloLive.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private static final String TASK_TL_ID_PREFIX = "TL-%s";

    private final TaskRepository taskRepository;
    private final TaskDtoMapper taskDtoMapper;

    @Override
    @Transactional
    public Task postTask(TaskDto taskDto) {
        if (taskDto.getTlId() == null) {
            taskDto.setTlId(generateTLId());
        }

        return taskRepository.save(taskDtoMapper.toTask(taskDto));
    }

    @Override
    @Transactional(readOnly = true)
    public GetTaskDto getByTLId(String tLId) {
        return taskDtoMapper.toGetTaskDto(taskRepository.findByTlId(tLId)
                .orElseThrow(() -> new TaskNotFoundException(tLId)));
    }

    private String generateTLId() {
        return format(TASK_TL_ID_PREFIX, taskRepository.count());
    }
}
