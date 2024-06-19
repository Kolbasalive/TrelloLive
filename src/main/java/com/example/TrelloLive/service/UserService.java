package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;
import com.example.TrelloLive.web.dto.user.UserDto;

import java.util.List;

public interface UserService {
    Users createUser(UserDto userDto);

    List<ResponseUserDto> getUsers();

    ResponseUserDto getUserById(String userId);

    Users deleteUsers(String userId);
}
