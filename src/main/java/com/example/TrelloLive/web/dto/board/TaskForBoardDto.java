package com.example.TrelloLive.web.dto.board;

import com.example.TrelloLive.web.dto.task.TagForTaskDto;
import com.example.TrelloLive.web.dto.task.UserForTaskDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class TaskForBoardDto {
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private String status;
    private String priority;
    private LocalDateTime deadline;
    private List<TagForTaskDto> tags;
    private List<UserForTaskDto> assignees;
    private String sprint;
    private String tlId;
}
