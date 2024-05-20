package com.example.TrelloLive.web.dto.task;

import com.example.TrelloLive.web.dto.board.BoardForTaskDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class TaskDto {
    @NotNull(message = "Name cannot be null")
    private String title;
    private String description;
    @NotNull(message = "Name cannot be null")
    private Date creationDate;
    @NotNull(message = "Name cannot be null")
    private String status;
    private String priority;
    private Date deadline;
    private List<TagForTaskDto> tags;
    private List<UserForTaskDto> assignees;
    @NotNull(message = "Name cannot be null")
    private BoardForTaskDto board;
    private String sprint;
    private String tlId;
}
