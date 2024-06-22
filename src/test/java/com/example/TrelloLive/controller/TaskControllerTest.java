package com.example.TrelloLive.controller;

import com.example.TrelloLive.service.Impl.TaskServiceImpl;
import com.example.TrelloLive.web.controllers.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void getTaskBuTlId_ReturnsResponseTaskDto() {

    }

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
