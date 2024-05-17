package com.example.TrelloLive.rest.controller;

import com.example.TrelloLive.rest.dto.TaskDto;
import com.example.TrelloLive.rest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trello/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/post")
    public String postTask(@RequestBody TaskDto taskDto){
        return ";";
    }

}
