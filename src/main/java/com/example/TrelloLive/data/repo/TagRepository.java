package com.example.TrelloLive.data.repo;

import com.example.TrelloLive.data.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {}