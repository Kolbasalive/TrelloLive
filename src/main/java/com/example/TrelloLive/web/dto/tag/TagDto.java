package com.example.TrelloLive.web.dto.tag;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TagDto {
    @NotNull(message = "name cannot be null")
    private String name;
}
