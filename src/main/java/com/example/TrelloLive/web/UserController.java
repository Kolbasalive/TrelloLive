package com.example.TrelloLive.web;

import com.example.TrelloLive.data.model.Users;
import com.example.TrelloLive.web.dto.ResponseDto;
import com.example.TrelloLive.web.dto.user.GetUserDto;
import com.example.TrelloLive.web.dto.user.UserDto;
import com.example.TrelloLive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trello/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/post")
    public ResponseDto postUser(@RequestBody UserDto userDto){
        return new ResponseDto(userService.createUser(userDto).getName());
    }

    @GetMapping("/get")
    public List<GetUserDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/get/{userId}")
    public GetUserDto getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }
}
