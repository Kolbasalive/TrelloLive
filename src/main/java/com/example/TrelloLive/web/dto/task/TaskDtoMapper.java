package com.example.TrelloLive.web.dto.task;

import com.example.TrelloLive.data.model.Task;

public interface TaskDtoMapper {

    Task toTask(TaskDto taskDto);
    GetTaskDto toGetTaskDto(Task task);

/*    GetTaskDto toGetTaskDto(Task task);*/


    //Tag mapToTagDto(TagDto source);


//    User mapToUserDto(UserDto source);
}
