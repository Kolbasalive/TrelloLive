package com.example.TrelloLive.service.Impl;

import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.data.repo.UserRepository;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.user.GetUserDto;
import com.example.TrelloLive.web.dto.user.UserDto;
import com.example.TrelloLive.web.dto.user.UserDtoMapper;
import com.example.TrelloLive.service.UserService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Users createUser(UserDto userDto) {
        return userRepository.save(userDtoMapper.toUser(userDto));
    }

    @Override
    public List<GetUserDto> getUsers() {
        return userRepository.findAll().stream().map(userDtoMapper::toGetUserDto).toList();
    }

    @Override
    public GetUserDto getUserById(String userId) {
        Optional<Users> users = userRepository.findById(UUID.fromString(userId));
        return users.map(userDtoMapper::toGetUserDto).orElse(null);
    }
}