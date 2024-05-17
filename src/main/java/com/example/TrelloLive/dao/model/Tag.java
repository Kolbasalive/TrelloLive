package com.example.TrelloLive.dao.model;

import jakarta.persistence.*;

import java.awt.*;
import java.util.UUID;

@Table(name = "Tag")
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tagId;
    @Column
    private String name;

    /*@Column
    private String color;*/
}
