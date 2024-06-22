package com.example.TrelloLive.web.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserForTaskDto {
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private String role;
}

