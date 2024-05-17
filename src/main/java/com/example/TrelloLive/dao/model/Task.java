package com.example.TrelloLive.dao.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Table(name="Task")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID taskId;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private String priority;
    @Column
    private Date deadline;
    @OneToOne
    @JoinColumn(name = "Tag_tagId")
    private Tag tag;
}
