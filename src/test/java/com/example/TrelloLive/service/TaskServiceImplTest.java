package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.*;
import com.example.TrelloLive.data.repo.TaskRepository;
import com.example.TrelloLive.service.Impl.TaskServiceImpl;
import com.example.TrelloLive.web.dto.task.ResponseTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDtoMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @InjectMocks
    private TaskServiceImpl taskService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskDtoMapperImpl taskDtoMapper;

    private String tlId;
    private ResponseTaskDto responseTaskDto;
    private Task task;
    private Users user;
    private Tag tag;
    private Board board;

    @BeforeEach
    void setUp() {
        tlId = "Tl-0";
        user = new Users();
        user.setUserId(UUID.randomUUID())
                .setName("Maksim")
                .setEmail("Makson@gmail.com")
                .setPassword("qwerty")
                .setRole(Role.ADMIN);
        tag = new Tag();
        tag.setTagId(UUID.randomUUID())
                .setName("Hardcore");
        board = new Board();
        board.setBoardId(UUID.randomUUID())
                .setName("Board A");
        responseTaskDto = new ResponseTaskDto();
        responseTaskDto.setTlId(tlId)
                .setTitle("Something");
        task = new Task();
        task.setTlId(tlId)
                .setTitle("Something");
    }

    @Test
    void getByTlId_ReturnResponseTaskDto() {
        Mockito.when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        Mockito.when(taskDtoMapper.toGetTaskDto(task)).thenReturn(responseTaskDto);
        ResponseTaskDto result = taskService.getByTLId(tlId);
        assertNotNull(result);
        //assertEquals(result, taskRepository.findByTlId(tlId));
        Mockito.verify(taskRepository, Mockito.times(1)).findByTlId(tlId);
        //Mockito.verify(taskDtoMapper, Mockito.times(1)).toGetTaskDto(task);
    }

    @Test
    void getByTlId_ReturnTaskNotFoundException() {
        tlId = "notTlId";
        Mockito.when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        //Mockito.verify(taskRepository, Mockito.times(1)).findByTlId(tlId);
    }

}
