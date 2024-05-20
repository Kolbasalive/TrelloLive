package com.example.TrelloLive.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "Tag")
@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tagId;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
