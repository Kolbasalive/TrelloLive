package com.example.TrelloLive.service.Impl;

import com.example.TrelloLive.data.model.Tag;
import com.example.TrelloLive.data.repo.TagRepository;
import com.example.TrelloLive.exception.TagNotFoundException;
import com.example.TrelloLive.service.TagService;
import com.example.TrelloLive.web.dto.tag.ResponseTagDto;
import com.example.TrelloLive.web.dto.tag.TagDto;
import com.example.TrelloLive.web.dto.tag.TagDtoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagDtoMapper tagDtoMapper;
    private final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    @Override
    public ResponseTagDto getTagById(String tagId) {
        Tag tag = tagRepository.findById(UUID.fromString(tagId))
                .orElseThrow(() -> new TagNotFoundException(tagId));
        return tagDtoMapper.toResponseTagDto(tag);
    }

    @Override
    public List<ResponseTagDto> getTags() {
        List<Tag> tagList = tagRepository.findAll();
        return tagList.stream().map(tagDtoMapper::toResponseTagDto).toList();
    }

    @Override
    public Tag createTag(TagDto tagDto) {
        Tag tag = tagDtoMapper.toTagFromTagDto(tagDto);
        logger.info("Tag create, tagName: " + tag.getName());
        return tagRepository.save(tag);
    }

    @Override
    public Tag removeTag(String tagId) {
        Tag tag = tagRepository.findById(UUID.fromString(tagId))
                .orElseThrow(() -> new TagNotFoundException(tagId));
        if (tag.getTasks() == null) {
            tagRepository.delete(tag);
            logger.info("Tag delete, tagId: " + tag.getTagId());
        } else {
            logger.info("The tag is used in the task");
        }
        return tag;
    }
}
