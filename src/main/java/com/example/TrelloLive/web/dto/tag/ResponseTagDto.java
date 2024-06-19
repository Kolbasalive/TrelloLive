package com.example.TrelloLive.web.dto.tag;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseTagDto {
    private UUID tagId;
    private String name;
}
