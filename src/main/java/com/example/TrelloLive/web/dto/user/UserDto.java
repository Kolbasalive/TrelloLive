package com.example.TrelloLive.web.dto.user;


import com.example.TrelloLive.data.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "Name cannot be null")
    @Pattern(
            regexp = "^[A-ZА-Я][a-zA-Zа-яА-Я]*$",
            message = "Name должно содержать имя с заглавной буквы"
    )
    private String name;
    private String email;
    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "role cannot be null")
    private Role role;
}
