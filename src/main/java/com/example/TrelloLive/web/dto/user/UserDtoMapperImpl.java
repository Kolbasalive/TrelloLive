package com.example.TrelloLive.web.dto.user;

import com.example.TrelloLive.data.model.Role;
import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.web.dto.task.UserForTaskDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapperImpl implements UserDtoMapper {
    @Override
    public Users toUser(UserDto userDto) {
        Users users = new Users();
        users.setUserId(userDto.getUserId());
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        users.setRole(userDto.getRole());

        return users;
    }

    @Override
    public ResponseUserDto toResponseUserDto(Users user) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getUserId());
        responseUserDto.setName(String.valueOf(user.getName()));
        responseUserDto.setEmail(user.getEmail());
        responseUserDto.setPassword(user.getPassword());
        responseUserDto.setRole(String.valueOf(user.getRole()));

        return responseUserDto;
    }

    @Override
    public Users toUsers(UserForTaskDto user) {
        Users users = new Users();
        users.setName(user.getName());
        users.setEmail(user.getEmail());
        users.setPassword(user.getPassword());
        users.setRole(Role.valueOf(user.getRole()));
        users.setUserId(user.getUserId());

        return users;
    }

    @Override
    public UserForTaskDto toUserForTaskDto(Users users) {
        UserForTaskDto userForTaskDto = new UserForTaskDto();
        userForTaskDto.setUserId(users.getUserId());
        userForTaskDto.setName(users.getName());
        userForTaskDto.setEmail(users.getEmail());
        userForTaskDto.setPassword(users.getPassword());
        userForTaskDto.setRole(String.valueOf(users.getRole()));

        return userForTaskDto;
    }
}
