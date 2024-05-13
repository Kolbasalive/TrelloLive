package com.example.TrelloLive.rest.dto.mapper;

import com.example.TrelloLive.dao.model.Role;
import com.example.TrelloLive.dao.model.Users;
import com.example.TrelloLive.rest.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public Users toUser(UserDto userDto) {
        Users users = new Users();
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        users.setRole(Role.valueOf(userDto.getRole()));
        return users;
    }
}
