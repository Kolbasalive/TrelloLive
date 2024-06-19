package com.example.TrelloLive.web.controllers;

import com.example.TrelloLive.service.UserService;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.user.ResponseUserDto;
import com.example.TrelloLive.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trello/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/post")
    public ResponseDto postUser(@RequestBody UserDto userDto) {
        return new ResponseDto(userService.createUser(userDto).getName());
    }

    @GetMapping("/get")
    public List<ResponseUserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/get/{userId}")
    public ResponseUserDto getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseDto deleteUsers(@PathVariable String userId) {
        return new ResponseDto(userService.deleteUsers(userId).getUserId().toString());
    }

}
