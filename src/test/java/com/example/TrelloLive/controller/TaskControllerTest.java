package com.example.TrelloLive.controller;

import com.example.TrelloLive.data.model.Task;
import com.example.TrelloLive.service.Impl.TaskServiceImpl;
import com.example.TrelloLive.web.controllers.TaskController;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.task.ResponseTaskDto;
import com.example.TrelloLive.web.dto.task.TaskDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Mock
    private TaskServiceImpl taskService;
    @InjectMocks
    private TaskController taskController;
    private MockMvc mockMvc;
    private ResponseTaskDto responseTaskDto;
    private ResponseDto responseDto;
    private String tlId;
    private String title;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        objectMapper = new ObjectMapper();
        tlId = "Tl-0";
        title = "Something";
        responseDto = new ResponseDto();
        responseTaskDto = new ResponseTaskDto();
        responseTaskDto.setTlId(tlId)
                .setTitle(title);
    }

    @Test
    void getTaskBuTlId_ReturnsResponseTaskDto() throws Exception {
        String responseTaskDtoJson = objectMapper.writeValueAsString(responseTaskDto);
        when(taskService.getByTLId(tlId)).thenReturn(responseTaskDto);
        mockMvc.perform(get("/trello/task/{tlId}", tlId))
                .andExpect(status().isOk())
                .andExpect(content().json(responseTaskDtoJson));
        verify(taskService, times(1)).getByTLId(tlId);
    }

    @Test
    void postTask_ReturnResponseDto() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(title).setTlId(tlId);
        Task task = new Task();
        task.setTlId(tlId)
                .setTitle(title);
        String taskDtoJson = objectMapper.writeValueAsString(taskDto);
        String responseDtoJson = objectMapper.writeValueAsString(responseDto);
        when(taskService.postTask(taskDto)).thenReturn(task);
        mockMvc.perform(post("/trello/live/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseDtoJson));
        verify(taskService, times(1)).postTask(taskDto);
    }

    @Test
    void postTask_ReturnsResponseDto() throws Exception {
        // Create a TaskDto object
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("New Task");

        // Create a Task object with a generated tlId
        Task task = new Task();
        task.setTlId("Tl-0");

        // Mock the behavior of the taskService to return the task
        when(taskService.postTask(taskDto)).thenReturn(task);

        // Convert the TaskDto object to JSON
        String taskDtoJson = objectMapper.writeValueAsString(taskDto);

        // Create the expected ResponseDto
        ResponseDto expectedResponseDto = new ResponseDto(task.getTlId());
        String expectedResponseDtoJson = objectMapper.writeValueAsString(expectedResponseDto);

        // Perform the POST request and check the response
        mockMvc.perform(post("/trello/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseDtoJson));

        // Verify that the taskService.postTask method was called once
        verify(taskService, times(1)).postTask(any(TaskDto.class));
    }

    @Test
    void deleteTask_ReturnResponseDto() {
    }

    @Test
    void putStatusInTask_ReturnResponseDto() {
    }

    @Test
    void getAssignees_ReturnListResponseUserDto() {
    }

    @Test
    void putAssigneesInTask_ReturnResponseDto() {
    }

    @Test
    void deleteAssigneesInTask_ReturnResponseDto() {
    }

    @Test
    void changeStatus_ReturnResponseDto() {
    }

    @Test
    void createTagInTask_ReturnResponseDto() {
    }

    @Test
    void getTaskByTagId_ReturnListResponseUserDto() {
    }

    @Test
    void deleteTagInTask_ReturnResponseDto() {
    }

    //.andExpect(content().json("{\"tlId\":\"Tl-0\",\"title\":\"Something\"}"));
/*    @Test
    void getTaskBuTlId_ReturnsResponseTaskDto() {
        String tlId = "Tl-0";
        String taskUUID = String.valueOf(UUID.randomUUID());
        ResponseTaskDto responseTaskDto = new ResponseTaskDto();
        responseTaskDto.setTlId(tlId)
                .setTitle("Board A");

        //when(taskService.getTaskByTlId(tlId)).thenReturn(Optional.of(responseTaskDto));

*//*        Task task = new Task();
        Board board = new Board();
        board.setBoardId(UUID.fromString("ec6d40e1-39db-4b07-bcb2-79f94c31a07f"));
        task.setTaskId(UUID.fromString("5d127eb9-07d1-4df1-b2a5-1251e1252398"))
                .setTitle("Task 1")
                .setDescription("Description for Task 1")
                .setCreationDate(LocalDateTime.parse("2024-05-25 08:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .setStatus(Status.IN_PROGRESS)
                .setPriority("high")
                .setDeadline(LocalDateTime.parse("2024-06-01 08:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .setBoard(board)
                .setSprint("Sprint 1")
                .setTlId("Tl-0");
        String tlId = "Tl-0";

        doReturn(task).when(this.taskRepository).findByTlId(tlId);
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));*//*

        var responseEntity = this.taskController.getTaskByTlId(tlId);
        var responseEntityServiceImpl = taskServiceImpl.getByTLId(tlId);

        var a = "123";
        System.out.println("Penis2");
        System.out.println("responseEntityServiceImpl " + responseEntityServiceImpl);
        System.out.println("responseEntity " + responseEntity);
        assertNotNull(a);
    }*/

}
