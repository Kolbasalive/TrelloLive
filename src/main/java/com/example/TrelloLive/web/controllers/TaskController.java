package com.example.TrelloLive.web.controllers;

import com.example.TrelloLive.service.TaskService;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.task.ResponseTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trello/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("")
    public ResponseDto postTask(@RequestBody TaskDto taskDto) {
        return new ResponseDto(taskService.postTask(taskDto).getTlId());
    }

    @GetMapping("/{tlId}")
    public ResponseTaskDto getTaskByTlId(@PathVariable String tlId) {
        return taskService.getByTLId(tlId);
    }

    @DeleteMapping("/delete/{tlId}")
    public ResponseDto deleteTask(@PathVariable String tlId) {
        return new ResponseDto(taskService.removeTask(tlId).getTlId());
    }

    @PutMapping("/put/status/{tlId}")
    public ResponseDto putStatusInTask(
            @RequestParam String status,
            @PathVariable String tlId) {
        return new ResponseDto(taskService.changeStatus(status, tlId));
    }

    @GetMapping("/assignees/{tlId}")
    public List<ResponseUserDto> getAssignees(@PathVariable String tlId) {
        return taskService.getAssigneesInTask(tlId);
    }

    @PostMapping("/assignees/{tlId}")
    public ResponseDto putAssigneesInTask(
            @RequestParam String userId,
            @PathVariable String tlId) {
        return new ResponseDto(taskService.addAssigneesInTask(userId, tlId)
                .getAssignees().getLast().getName());
    }

    @DeleteMapping("/assignees/{tlId}")
    public ResponseDto deleteAssigneesInTask(
            @PathVariable String tlId,
            @RequestParam String userId) {
        return new ResponseDto(taskService.deleteAssigneesInTask(userId, tlId)
                .getAssignees().getLast().getUserId().toString());
    }

    @PostMapping("/status/{tlId}")
    public ResponseDto changeStatus(
            @PathVariable String tlId,
            @RequestParam String status) {
        return new ResponseDto(taskService.changeStatus(status, tlId));
    }

    @PostMapping("/tag/{tlId}")
    public ResponseDto createTagInTask(
            @RequestParam String tagId,
            @PathVariable String tlId) {
        return new ResponseDto(taskService.createOrSetTagInTask(tagId, tlId)
                .getTags().getLast().getTagId().toString());
    }

    @GetMapping("byTag/{tagId}")
    public List<ResponseTaskDto> getTaskByTagId(@PathVariable String tagId) {
        return taskService.getTaskByTagId(tagId);
    }

    @DeleteMapping("/tag/{tlId}")
    public ResponseDto deleteTagInTask(
            @RequestParam String tagId,
            @PathVariable String tlId) {
        return new ResponseDto(taskService.deleteTagInTask(tagId, tlId)
                .getTags().getLast().getTagId().toString());
    }


}
