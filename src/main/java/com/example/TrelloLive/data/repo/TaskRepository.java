package com.example.TrelloLive.data.repo;

import com.example.TrelloLive.data.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findByTlId(String TLId);

    @Query("SELECT t FROM Task t JOIN t.tags tag WHERE tag.tagId = :tagId")
    List<Task> findTasksByTagId(@Param("tagId") UUID tagId);
}