package com.example.TrelloLive.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Table(name = "Tag")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Tag{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tagId;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<Task> tasks;
}
