package com.example.TrelloLive.data.repo;

import com.example.TrelloLive.data.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
}