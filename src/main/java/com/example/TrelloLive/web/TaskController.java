package com.example.TrelloLive.web;

import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.task.GetTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;
import com.example.TrelloLive.web.dto.task.TaskDtoMapper;
import com.example.TrelloLive.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("trello/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/post")
    public ResponseDto postTask(@RequestBody TaskDto taskDto){
        return new ResponseDto(taskService.postTask(taskDto).getTlId());
    }

    @GetMapping("/{tlId}")
    private GetTaskDto getById(@PathVariable String tlId) {
        return taskService.getByTLId(tlId);
    }
}
