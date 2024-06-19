package com.example.TrelloLive.web.dto.task;

import com.example.TrelloLive.web.dto.board.BoardForTaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseTaskDto {
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private String status;
    private String priority;
    private LocalDateTime deadline;
    private String sprint;//
    private String tlId;
    private List<TagForTaskDto> tags;
    private List<UserForTaskDto> assignees;
    private BoardForTaskDto board;
}