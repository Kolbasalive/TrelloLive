package com.example.TrelloLive.service.Impl;

import com.example.TrelloLive.data.model.Status;
import com.example.TrelloLive.data.model.Tag;
import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.data.repo.TagRepository;
import com.example.TrelloLive.data.repo.TaskRepository;
import com.example.TrelloLive.data.repo.UserRepository;
import com.example.TrelloLive.exception.TagNotFoundException;
import com.example.TrelloLive.exception.TaskNotFoundException;
import com.example.TrelloLive.exception.UserNotFoundException;
import com.example.TrelloLive.service.TaskService;
import com.example.TrelloLive.web.dto.task.ResponseTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;
import com.example.TrelloLive.web.dto.task.TaskDtoMapper;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;
import com.example.TrelloLive.web.dto.user.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private static final String TASK_TL_ID_PREFIX = "Tl-%s";
    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TaskDtoMapper taskDtoMapper;
    private final UserDtoMapper userDtoMapper;

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
    public ResponseTaskDto getByTLId(String tLId) {
        return taskDtoMapper.toGetTaskDto(taskRepository.findByTlId(tLId)
                .orElseThrow(() -> new TaskNotFoundException(tLId)));
    }

    @Override
    public String changeStatus(String status, String tlId) {
        Task task = taskRepository.findByTlId(tlId)
                .orElseThrow(() -> new TaskNotFoundException(tlId));
        task.setStatus(Status.valueOf(status));

        return taskRepository.save(task).getStatus().name();
    }

    @Override
    public List<ResponseUserDto> getAssigneesInTask(String tlId) {
        Optional<Task> optionalTask = taskRepository.findByTlId(tlId);
        if (optionalTask.isPresent()) {
            List<Users> usersList = new ArrayList<>(optionalTask.get().getAssignees());

            return usersList.stream().map(userDtoMapper::toResponseUserDto).toList();
        }
        return null;
    }

    @Override
    public Task addAssigneesInTask(String userId, String tlId) {
        Task task = taskRepository.findByTlId(tlId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Users users = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        task.getAssignees().add(users);

        return taskRepository.save(task);
    }

    @Override
    public Task createOrSetTagInTask(String tagId, String tlId) {
        Task task = taskRepository.findByTlId(tlId).orElseThrow(
                () -> new TaskNotFoundException(tlId));
        logger.info("CreateTagInTask, TaskId: " + task.getTaskId());
        Tag tag = tagRepository.findById(UUID.fromString(tagId)).orElseThrow(
                () -> new TagNotFoundException(tagId));
        logger.info("CreateTagInTask, TagName: " + tag.getName());
        logger.info("CreateTagInTask, TagId: " + tag.getTagId());
        if (!task.getTags().contains(tag)) {
            task.getTags().add(tag);

            return taskRepository.save(task);
        }
        logger.info("Tag add in task: " + task.getTags().getLast().getTagId().toString());

        return null;
    }

    @Override
    public Task deleteAssigneesInTask(String userId, String tlId) {
        Task task = taskRepository.findByTlId(tlId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Users users = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        task.getAssignees().remove(users);

        return taskRepository.save(task);
    }

    @Override
    public Task deleteTagInTask(String tagId, String tlId) {
        Task task = taskRepository.findByTlId(tlId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Tag tag = tagRepository.findById(UUID.fromString(tagId)).orElseThrow(
                () -> new TagNotFoundException(tagId));
        task.getTags().remove(tag);

        return taskRepository.save(task);
    }

    @Override
    public List<ResponseTaskDto> getTaskByTagId(String tagId) {
        List<Task> taskList = taskRepository.findTasksByTagId(UUID.fromString(tagId));
        logger.info("TaskByTagId, taskName: " + taskList.getFirst().getTitle());

        return taskList.stream().map(taskDtoMapper::toGetTaskDto).toList();
    }

    @Override
    public Task removeTask(String tlId) {
        Task task = taskRepository.findByTlId(tlId)
                .orElseThrow(() -> new TaskNotFoundException(tlId));
        taskRepository.delete(task);
        logger.info("Delete Task, taskTlId: " + task.getTlId());

        return task;
    }

    private String generateTLId() {
        return format(TASK_TL_ID_PREFIX, taskRepository.count());
    }
}
