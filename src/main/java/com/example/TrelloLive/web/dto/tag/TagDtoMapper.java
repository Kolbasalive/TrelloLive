package com.example.TrelloLive.web.dto.tag;

import com.example.TrelloLive.data.model.Tag;
import com.example.TrelloLive.web.dto.task.TagForTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;


public interface TagDtoMapper {
    Tag toTag(TagForTaskDto taskDto);
    TagForTaskDto toTagForTaskDto(Tag tag);

}
