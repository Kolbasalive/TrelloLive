package com.example.TrelloLive.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "task")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID taskId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "priority", nullable = false)
    private String priority;
    @Column(name = "deadline")
    private LocalDateTime deadline;
    @ManyToMany
    @JoinTable(
            name = "task_tag",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "task_assignees",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> assignees = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
    @Column(name = "sprint")
    private String sprint;
    @Column(name = "tlId")
    private String tlId;
}
