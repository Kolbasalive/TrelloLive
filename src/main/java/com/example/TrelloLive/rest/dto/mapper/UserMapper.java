package com.example.TrelloLive.rest.dto.mapper;

import com.example.TrelloLive.dao.model.Users;
import com.example.TrelloLive.rest.dto.UserDto;

public interface UserMapper {
    Users toUser(UserDto userDto);
}
