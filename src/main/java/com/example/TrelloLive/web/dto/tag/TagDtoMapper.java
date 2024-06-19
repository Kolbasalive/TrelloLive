package com.example.TrelloLive.web.dto.tag;

import com.example.TrelloLive.data.model.Tag;
import com.example.TrelloLive.web.dto.task.TagForTaskDto;


public interface TagDtoMapper {
    Tag toTag(TagForTaskDto taskDto);
    TagForTaskDto toTagForTaskDto(Tag tag);
    ResponseTagDto toResponseTagDto(Tag tag);
    Tag toTagFromTagDto(TagDto tagDto);
}
