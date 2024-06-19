package com.example.TrelloLive.web.dto.user;

import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.web.dto.task.UserForTaskDto;

public interface UserDtoMapper {
    Users toUser(UserDto userDto);
    ResponseUserDto toResponseUserDto(Users user);

    Users toUsers(UserForTaskDto user);

    UserForTaskDto toUserForTaskDto(Users users);

}