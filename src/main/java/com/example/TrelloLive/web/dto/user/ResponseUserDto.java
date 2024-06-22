package com.example.TrelloLive.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseUserDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String role;
}
