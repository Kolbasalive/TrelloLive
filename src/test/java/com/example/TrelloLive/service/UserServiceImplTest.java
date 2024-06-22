package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Role;
import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.data.repo.UserRepository;
import com.example.TrelloLive.exception.UserNotFoundException;
import com.example.TrelloLive.service.Impl.UserServiceImpl;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;
import com.example.TrelloLive.web.dto.user.UserDto;
import com.example.TrelloLive.web.dto.user.UserDtoMapperImpl;
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
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDtoMapperImpl userDtoMapper;
    @InjectMocks
    private UserServiceImpl userService;

    private Users user;
    private UUID userId;
    private ResponseUserDto responseUserDto;
    @BeforeEach
    void setUp(){
        userId = UUID.randomUUID();
        user = new Users();
        user.setUserId(userId)
                .setName("Maksim")
                .setRole(Role.ADMIN);
        responseUserDto = new ResponseUserDto();
        responseUserDto.setId(userId)
                .setName("Maksim")
                .setRole(Role.ADMIN.toString());
    }

    @Test
    void createUser_ReturnUsers(){
        UserDto userDto = new UserDto();
        userDto.setUserId(userId)
                .setName("Maksim")
                .setRole(Role.ADMIN);
        when(userDtoMapper.toUser(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        Users result = userService.createUser(userDto);
        assertEquals(result.getUserId(), userDto.getUserId());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUsers_ReturnListResponseUserDto(){
        List<ResponseUserDto> userDtoList = List.of(responseUserDto);
        when(userDtoMapper.toResponseUserDto(user)).thenReturn(responseUserDto);
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<ResponseUserDto> result = userService.getUsers();
        assertNotNull(result);
        assertEquals(result.size(), userDtoList.size());
        assertEquals(result.getFirst().getId(), userDtoList.getFirst().getId());
    }

    @Test
    void getUserById_ReturnUserNotFound(){
        userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(userId.toString()));
    }

    @Test
    void getUserById_ReturnResponseUserDto(){
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userDtoMapper.toResponseUserDto(user)).thenReturn(responseUserDto);
        ResponseUserDto result = userService.getUserById(userId.toString());
        assertNotNull(result);
        assertEquals(result.getId(), responseUserDto.getId());
    }

    @Test
    void deleteUsers_ReturnUserNotFoundException(){
        userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                () -> userService.deleteUsers(userId.toString()));
    }

    @Test
    void deleteUsers_ReturnUsers(){
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Users result = userService.deleteUsers(userId.toString());
        assertNotNull(result);
        assertEquals(result.getUserId(), userId);
    }
}
