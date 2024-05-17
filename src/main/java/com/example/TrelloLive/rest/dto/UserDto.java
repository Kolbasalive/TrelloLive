package com.example.TrelloLive.rest.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserDto {
    @NotNull(message = "Name cannot be null")
    @Pattern(
            regexp = "^[A-ZА-Я][a-zA-Zа-яА-Я]*$",
            message = "Name должно содержать имя с заглавной буквы"
    )
    private String name;
    @NotNull(message = "email cannot be null")
    private String email;
    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "role cannot be null")
    private String role;
}
