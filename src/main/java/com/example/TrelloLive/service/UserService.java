package com.example.TrelloLive.service;

import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.user.GetUserDto;
import com.example.TrelloLive.web.dto.user.UserDto;

import java.util.List;

public interface UserService {
    Users createUser(UserDto userDto);

    List<GetUserDto> getUsers();

    GetUserDto getUserById(String userId);
}
