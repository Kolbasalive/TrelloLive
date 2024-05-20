package com.example.TrelloLive.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "assignees")
    private List<Task> taskList = new ArrayList<>();
}
