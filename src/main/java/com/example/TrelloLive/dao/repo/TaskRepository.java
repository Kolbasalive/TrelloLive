package com.example.TrelloLive.dao.repo;

import com.example.TrelloLive.dao.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {}