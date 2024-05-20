package com.example.TrelloLive.web.dto.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForTaskDto {
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private String role;
}

