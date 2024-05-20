package com.example.TrelloLive.data.repo;

import com.example.TrelloLive.data.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findByTlId(String TLId);
}