package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.*;
import com.example.TrelloLive.data.repo.TagRepository;
import com.example.TrelloLive.data.repo.TaskRepository;
import com.example.TrelloLive.data.repo.UserRepository;
import com.example.TrelloLive.exception.TagNotFoundException;
import com.example.TrelloLive.exception.TaskNotFoundException;
import com.example.TrelloLive.exception.UserNotFoundException;
import com.example.TrelloLive.service.Impl.TaskServiceImpl;
import com.example.TrelloLive.web.dto.task.*;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;
import com.example.TrelloLive.web.dto.user.UserDtoMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @InjectMocks
    @Spy
    private TaskServiceImpl taskService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TaskDtoMapperImpl taskDtoMapper;
    @Mock
    private UserDtoMapperImpl userDtoMapper;

    private String tlId;
    private ResponseTaskDto responseTaskDto;
    private Task task;
    private Users user;
    private UserForTaskDto userForTaskDto;
    private Tag tag;

    @BeforeEach
    void setUp() {
        tlId = "Tl-0";
        user = new Users();
        user.setUserId(UUID.randomUUID())
                .setName("Maksim")
                .setRole(Role.ADMIN);
        userForTaskDto = new UserForTaskDto();
        userForTaskDto.setUserId(user.getUserId())
                .setName(user.getName())
                .setRole(user.getRole().toString());
        tag = new Tag();
        tag.setTagId(UUID.randomUUID())
                .setName("Hardcore");
        responseTaskDto = new ResponseTaskDto();
        responseTaskDto.setTlId(tlId)
                .setTitle("Something")
                .setAssignees(List.of(userForTaskDto));
        task = new Task();
        task.setTlId(tlId)
                .setTitle("Something")
                .setStatus(Status.OPEN)
                .setAssignees(new ArrayList<>(List.of(user)));
    }

    @Test
    void getByTlId_ReturnResponseTaskDto() {
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(taskDtoMapper.toGetTaskDto(task)).thenReturn(responseTaskDto);
        ResponseTaskDto result = taskService.getByTLId(tlId);
        assertNotNull(result);
        assertEquals(result.getTlId(), responseTaskDto.getTlId());

        verify(taskRepository, times(1)).findByTlId(tlId);
        verify(taskDtoMapper, times(1)).toGetTaskDto(task);
    }

    @Test
    void getByTlId_ReturnTaskNotFoundException() {
        tlId = "notTlId";
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.getByTLId(tlId));

        verify(taskRepository, times(1)).findByTlId(tlId);
        verify(taskDtoMapper, Mockito.never()).toGetTaskDto(task);
    }

    @Test
    void changeStatus_ReturnString() {
        Status newStatus = Status.DONE;
        Task updateTask = new Task();
        updateTask.setTlId(task.getTlId())
                .setTitle(task.getTitle())
                .setStatus(newStatus);
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(updateTask);
        String result = taskService.changeStatus(newStatus.toString(), tlId);

        assertNotNull(result);
        assertEquals(result, newStatus.toString());
    }

    @Test
    void changeStatus_ReturnTaskNotFoundException() {
        tlId = "notTlId";
        Status newStatus = Status.DONE;
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.changeStatus(newStatus.toString(), tlId));

        verify(taskRepository, times(1)).findByTlId(tlId);
        verify(taskRepository, never()).save(any());
    }

    @Test
    void postTask_TlIdIsProvided_ReturnTask() {
        TaskDto taskDto = new TaskDto();
        taskDto.setTlId(tlId)
                .setTitle("Something");
        when(taskDtoMapper.toTask(taskDto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.postTask(taskDto);
        assertNotNull(result);
        assertEquals(result.getTlId(), taskDto.getTlId());

        verify(taskDtoMapper, times(1)).toTask(taskDto);
        verify(taskRepository, times(1)).save(task);
        verify(taskService, never()).generateTLId();
    }

    @Test
    void postTask_TlIdIsNull_ReturnTask() {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Something");
        when(taskDtoMapper.toTask(taskDto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.postTask(taskDto);
        assertNotNull(result);
        assertEquals(result.getTlId(), taskDto.getTlId());

        verify(taskDtoMapper, times(1)).toTask(taskDto);
        verify(taskRepository, times(1)).save(task);
        verify(taskService, times(1)).generateTLId();
    }

    @Test
    void getAssigneesInTask_ReturnListResponseUserDto() {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getUserId())
                .setName("Maksim")
                .setRole(Role.ADMIN.toString());
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(userDtoMapper.toResponseUserDto(user)).thenReturn(responseUserDto);
        List<ResponseUserDto> result = taskService.getAssigneesInTask(tlId);
        assertNotNull(result);
        assertTrue(result.contains(responseUserDto));

        verify(taskRepository, times(1)).findByTlId(tlId);
        verify(userDtoMapper, times(1)).toResponseUserDto(user);
        verify(taskService, times(1)).getAssigneesInTask(tlId);
    }

    @Test
    void getAssigneesInTask_ReturnNull() {
        tlId = "notTlId";
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        List<ResponseUserDto> result = taskService.getAssigneesInTask(tlId);
        assertNull(result);
        verify(taskRepository, times(1)).findByTlId(tlId);
    }

    @Test
    void addAssigneesInTask_ReturnTask() {
        Users newUser = new Users();
        newUser.setUserId(UUID.randomUUID())
                .setName("David")
                .setRole(Role.MEMBER);
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(userRepository.findById(newUser.getUserId())).thenReturn(Optional.of(newUser));
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.addAssigneesInTask(newUser.getUserId().toString(), tlId);
        assertNotNull(result);
        assertTrue(result.getAssignees().contains(newUser));
    }


    @Test
    void addAssigneesInTask_ReturnTaskNotFoundException() {
        tlId = "notTliId";
        String userId = user.getUserId().toString();
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.addAssigneesInTask(userId, tlId));
    }

    @Test
    void addAssigneesInTask_ReturnUserNotFoundException() {
        UUID userId = UUID.randomUUID();
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> taskService.addAssigneesInTask(userId.toString(), tlId));
    }

    @Test
    void createOrSetTagInTask_ReturnTaskNotFoundException() {
        tlId = "notTlId";
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class,
                () -> taskService.createOrSetTagInTask(tag.getTagId().toString(), tlId));
    }

    @Test
    void createOrSetTagInTask_ReturnTagNotFoundException() {
        UUID newTag = UUID.randomUUID();
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(tagRepository.findById(newTag)).thenReturn(Optional.empty());
        assertThrows(TagNotFoundException.class, () -> taskService.createOrSetTagInTask(newTag.toString(),tlId));
    }

    @Test
    void createOrSetTagInTask_ReturnTask() {
        task.setTags(new ArrayList<>());
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(tagRepository.findById(tag.getTagId())).thenReturn(Optional.of(tag));
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.createOrSetTagInTask(tag.getTagId().toString(),tlId);
        assertNotNull(result);
        assertTrue(result.getTags().contains(tag));

        verify(taskRepository, Mockito.times(1)).findByTlId(tlId);
        verify(tagRepository, Mockito.times(1)).findById(UUID.fromString(tag.getTagId().toString()));
        verify(taskRepository, Mockito.times(1)).save(task);
    }
    @Test
    void createOrSetTagInTask_ReturnNull() {
        task.setTags(List.of(tag));
        when(taskRepository.findByTlId(task.getTlId())).thenReturn(Optional.of(task));
        when(tagRepository.findById(tag.getTagId())).thenReturn(Optional.of(tag));
        Task result = taskService.createOrSetTagInTask(tag.getTagId().toString(), task.getTlId());
        assertNull(result);
    }
    @Test
    void deleteAssigneesInTask_ReturnTaskNotFoundException(){
        tlId = "notTlId";
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class,
                () -> taskService.deleteAssigneesInTask(user.getUserId().toString(), tlId));
    }
    @Test
    void deleteAssigneesInTask_ReturnUserNOtFoundException(){
        UUID userId = UUID.randomUUID();
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> taskService.deleteAssigneesInTask(userId.toString(),tlId));
    }
    @Test
    void deleteAssigneesInTask_ReturnTask(){
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(userRepository.findById(UUID.fromString(user.getUserId().toString()))).thenReturn(Optional.of(user));
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.deleteAssigneesInTask(user.getUserId().toString(), tlId);
        assertNotNull(result);
        assertEquals(0, result.getAssignees().size());
        assertFalse(result.getAssignees().contains(user));
    }

    @Test
    void deleteTagInTask_ReturnTaskNotFoundException(){
        tlId = "notTlId";
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class,
                () -> taskService.deleteTagInTask(user.getUserId().toString(), tlId));
    }
    @Test
    void deleteTagInTask_ReturnTagNotFoundException(){
        UUID tagId = UUID.randomUUID();
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());
        assertThrows(TagNotFoundException.class,
                () -> taskService.deleteTagInTask(tagId.toString(),tlId));
    }
    @Test
    void deleteTagInTask_ReturnTask(){
        task.setTags(new ArrayList<>(List.of(tag)));
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        when(tagRepository.findById(UUID.fromString(tag.getTagId().toString()))).thenReturn(Optional.of(tag));
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.deleteTagInTask(tag.getTagId().toString(), tlId);
        assertNotNull(result);
        assertEquals(0, result.getTags().size());
        assertFalse(result.getTags().contains(tag));
    }

    @Test
    void getTaskByTagId_ReturnListResponseTaskDto(){
        Tag tag1 = new Tag();
        tag1.setTagId(UUID.randomUUID());
        TagForTaskDto taskDto = new TagForTaskDto();
        taskDto.setTagId(tag1.getTagId());
        ResponseTaskDto responseTaskDto1 = new ResponseTaskDto();
        responseTaskDto1.setTlId("Tl-1")
                .setTitle("Something")
                .setAssignees(List.of(userForTaskDto))
                .setTags(List.of(taskDto));
        Task task1 = new Task();
        task1.setTlId("Tl-1")
                .setTitle("Something")
                .setStatus(Status.OPEN)
                .setAssignees(new ArrayList<>(List.of(user)))
                .setTags(List.of(tag1));
        List<Task> taskList = List.of(task, task1);
        when(taskRepository.findTasksByTagId(tag.getTagId())).thenReturn(taskList);
        when(taskDtoMapper.toGetTaskDto(task)).thenReturn(responseTaskDto);
        when(taskDtoMapper.toGetTaskDto(task1)).thenReturn(responseTaskDto1);
        System.out.println(UUID.randomUUID());
        List<ResponseTaskDto> result = taskService.getTaskByTagId(tag.getTagId().toString());
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void removeTask_ReturnTaskNotFoundException(){
        tlId = "notTlId";
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.removeTask(tlId));
    }

    @Test
    void removeTask_ReturnTask(){
        when(taskRepository.findByTlId(tlId)).thenReturn(Optional.of(task));
        Task result = taskService.removeTask(tlId);
        assertEquals(result.getTlId(), tlId);
    }
}