package com.example.TrelloLive.rest.service;

import com.example.TrelloLive.rest.dto.UserDto;

public interface UserService {
    String postUser(UserDto userDto);
}
