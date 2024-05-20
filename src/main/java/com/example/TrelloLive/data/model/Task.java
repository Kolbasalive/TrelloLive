package com.example.TrelloLive.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name="task")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
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
    @OneToMany(mappedBy = "task")
    private List<Tag> tags = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "task_assignees",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> assignees = new ArrayList<>();
    @ManyToOne
    private Board board;
    private String sprint;
    private String tlId;
}
