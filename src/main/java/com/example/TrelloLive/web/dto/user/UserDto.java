package com.example.TrelloLive.web.dto.user;


import com.example.TrelloLive.data.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto {
    private UUID userId;
    @NotNull(message = "name cannot be null")
    @Pattern(
            regexp = "^[A-ZА-Я][a-zA-Zа-яА-Я]*$",
            message = "name должно содержать имя с заглавной буквы"
    )
    private String name;
    @NotNull(message = "email cannot be null")
    private String email;
    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "role cannot be null")
    private Role role;
}
