package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Tag;
import com.example.TrelloLive.data.repo.TagRepository;
import com.example.TrelloLive.exception.TagNotFoundException;
import com.example.TrelloLive.service.Impl.TagServiceImpl;
import com.example.TrelloLive.web.dto.tag.ResponseTagDto;
import com.example.TrelloLive.web.dto.tag.TagDto;
import com.example.TrelloLive.web.dto.tag.TagDtoMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagDtoMapperImpl tagDtoMapper;
    @InjectMocks
    private TagServiceImpl tagService;
    private Tag tag;
    private UUID tagId;
    private ResponseTagDto responseTagDto;
    @BeforeEach
    void setUp(){
        tagId = UUID.randomUUID();
        tag = new Tag();
        tag.setTagId(tagId)
                .setName("Tag A");
        responseTagDto = new ResponseTagDto();
        responseTagDto.setTagId(tagId)
                .setName("Tag A");
    }

    @Test
    void getTagById_ReturnResponseTagDto(){
        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(tagDtoMapper.toResponseTagDto(tag)).thenReturn(responseTagDto);
        ResponseTagDto result = tagService.getTagById(tagId.toString());

        assertNotNull(result);
        assertEquals(result.getTagId(), responseTagDto.getTagId());
    }

    @Test
    void getTagById_ReturnTagNotFoundException(){
        tagId = UUID.randomUUID();
        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());
        assertThrows(TagNotFoundException.class,
                () -> tagService.getTagById(tagId.toString()));
    }

    @Test
    void getTags(){
        Tag tag1 = new Tag();
        tag1.setTagId(UUID.randomUUID())
                .setName("Tag B");
        ResponseTagDto responseTagDto1 = new ResponseTagDto();
        responseTagDto1.setTagId(tag1.getTagId())
                .setName("Tag B");
        List<ResponseTagDto> tagDtoList = List.of(responseTagDto,responseTagDto1);
        when(tagRepository.findAll()).thenReturn(List.of(tag,tag1));
        when(tagDtoMapper.toResponseTagDto(tag)).thenReturn(responseTagDto);
        when(tagDtoMapper.toResponseTagDto(tag1)).thenReturn(responseTagDto1);
        List<ResponseTagDto> result = tagService.getTags();

        assertNotNull(result);
        assertEquals(result.size(), tagDtoList.size());
        assertEquals(result.get(0).getTagId(), tagDtoList.get(0).getTagId());
        assertEquals(result.get(1).getTagId(), tagDtoList.get(1).getTagId());
    }

    @Test
    void createTag_ReturnTag(){
        TagDto tagDto = new TagDto();
        tagDto.setName("Tag A");
        when(tagRepository.save(tag)).thenReturn(tag);
        when(tagDtoMapper.toTagFromTagDto(tagDto)).thenReturn(tag);
        Tag result = tagService.createTag(tagDto);

        assertNotNull(result);
        assertEquals(result.getName(), tagDto.getName());
    }

    @Test
    void removeTag_ReturnTagNotFoundException(){
        tagId = UUID.randomUUID();
        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());
        assertThrows(TagNotFoundException.class,
                () -> tagService.removeTag(tagId.toString()));
    }

    @Test
    void removeTag_ReturnTag(){
        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        tag.setTasks(null);
        Tag result = tagService.removeTag(tagId.toString());

        assertNotNull(result);
        assertEquals(result.getTagId(), tag.getTagId());
        verify(tagRepository, times(1)).delete(tag);
    }

}
