package com.example.TrelloLive.web.dto.tag;

import com.example.TrelloLive.data.model.Tag;
import com.example.TrelloLive.web.dto.task.TagForTaskDto;
import org.springframework.stereotype.Component;

@Component
public class TagDtoMapperImpl implements TagDtoMapper {
    @Override
    public Tag toTag(TagForTaskDto taskDto) {
        Tag tag = new Tag();
        tag.setTagId(taskDto.getTagId());
        tag.setName(taskDto.getName());

        return tag;
    }


    @Override
    public TagForTaskDto toTagForTaskDto(Tag tag) {
        TagForTaskDto tagForTaskDto = new TagForTaskDto();
        tagForTaskDto.setTagId(tag.getTagId());
        tagForTaskDto.setName(tag.getName());

        return tagForTaskDto;
    }

    @Override
    public ResponseTagDto toResponseTagDto(Tag tag) {
        ResponseTagDto responseTagDto = new ResponseTagDto();
        responseTagDto.setTagId(tag.getTagId())
                .setName(tag.getName());

        return responseTagDto;
    }

    @Override
    public Tag toTagFromTagDto(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());

        return tag;
    }
}
