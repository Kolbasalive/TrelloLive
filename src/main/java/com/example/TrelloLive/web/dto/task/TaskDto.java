package com.example.TrelloLive.web.dto.task;

import com.example.TrelloLive.web.dto.board.BoardForTaskDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class TaskDto {
    private UUID taskId;
    @NotNull(message = "Name cannot be null")
    private String title;
    private String description;
    @NotNull(message = "Name cannot be null")
    private LocalDateTime creationDate;
    @NotNull(message = "Name cannot be null")
    private String status;
    private String priority;
    private LocalDateTime deadline;
    private List<TagForTaskDto> tags;
    private List<UserForTaskDto> assignees;
    @NotNull(message = "Name cannot be null")
    private BoardForTaskDto board;
    private String sprint;
    private String tlId;
}
