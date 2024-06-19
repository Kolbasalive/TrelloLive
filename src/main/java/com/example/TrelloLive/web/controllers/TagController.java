package com.example.TrelloLive.web.controllers;

import com.example.TrelloLive.service.TagService;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.tag.ResponseTagDto;
import com.example.TrelloLive.web.dto.tag.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trello/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("{tagId}")
    public ResponseTagDto getTagById(@PathVariable String tagId) {
        return tagService.getTagById(tagId);
    }

    @GetMapping
    public List<ResponseTagDto> getTags() {
        return tagService.getTags();
    }

    @PostMapping()
    public ResponseDto postTag(@RequestBody TagDto tagDto) {
        return new ResponseDto(tagService.createTag(tagDto).getTagId().toString());
    }

    @DeleteMapping("{tagId}")
    public ResponseDto deleteTag(@PathVariable String tagId) {
        return new ResponseDto(tagService.removeTag(tagId).getTagId().toString());
    }
}
