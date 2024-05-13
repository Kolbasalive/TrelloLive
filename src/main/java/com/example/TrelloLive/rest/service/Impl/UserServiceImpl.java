package com.example.TrelloLive.rest.service.Impl;

import com.example.TrelloLive.dao.repo.UserRepository;
import com.example.TrelloLive.rest.dto.UserDto;
import com.example.TrelloLive.rest.dto.mapper.UserMapper;
import com.example.TrelloLive.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public String postUser(UserDto userDto) {
        userRepository.save(userMapper.toUser(userDto));
        return "OK";
    }

}
