package com.example.TrelloLive;
// Import the necessary packages
import com.example.TrelloLive.service.TaskService;
import com.example.TrelloLive.web.controllers.TaskController;
import com.example.TrelloLive.web.dto.task.ResponseTaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testGetTaskByTlId() throws Exception {
        // Mock the service response
        ResponseTaskDto mockResponse = new ResponseTaskDto();
        mockResponse.setTlId("Tl-10");
        mockResponse.setTitle("Sample Task");
        // ... other fields

        when(taskService.getByTLId(anyString())).thenReturn(mockResponse);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/tasks/{tlId}", "some-tlId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"tlId\":\"Tl-10\",\"title\":\"Sample Task\"}")); // Include other fields as necessary
    }
}
