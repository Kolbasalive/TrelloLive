package com.example.TrelloLive.service.Impl;

import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.data.repo.UserRepository;
import com.example.TrelloLive.exception.UserNotFoundException;
import com.example.TrelloLive.service.UserService;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;
import com.example.TrelloLive.web.dto.user.UserDto;
import com.example.TrelloLive.web.dto.user.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public Users createUser(UserDto userDto) {
        return userRepository.save(userDtoMapper.toUser(userDto));
    }

    @Override
    public List<ResponseUserDto> getUsers() {
        return userRepository.findAll().stream().map(userDtoMapper::toResponseUserDto).toList();
    }

    @Override
    public ResponseUserDto getUserById(String userId) {
        Optional<Users> users = userRepository.findById(UUID.fromString(userId));
        return users.map(userDtoMapper::toResponseUserDto).orElseThrow(
                () -> new UserNotFoundException(userId));
    }

    @Override
    public Users deleteUsers(String userId) {
        Users users = userRepository.findById(UUID.fromString(userId)).orElseThrow(
                () -> new UserNotFoundException(userId));
        userRepository.delete(users);

        return users;
    }
}