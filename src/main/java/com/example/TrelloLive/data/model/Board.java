package com.example.TrelloLive.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "board")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Board{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID boardId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Task> tasks = new ArrayList<>();
}
