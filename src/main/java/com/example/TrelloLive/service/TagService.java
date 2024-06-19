package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Tag;
import com.example.TrelloLive.web.dto.tag.ResponseTagDto;
import com.example.TrelloLive.web.dto.tag.TagDto;

import java.util.List;

public interface TagService {
    ResponseTagDto getTagById(String tagId);

    List<ResponseTagDto> getTags();

    Tag createTag(TagDto tagDto);

    Tag removeTag(String tagId);
}
