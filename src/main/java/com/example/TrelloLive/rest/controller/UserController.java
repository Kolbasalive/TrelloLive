package com.example.TrelloLive.rest.controller;

import com.example.TrelloLive.rest.dto.UserDto;
import com.example.TrelloLive.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/put")
    public String setUser(@RequestBody UserDto userDto){
        return userService.postUser(userDto);
    }
}
