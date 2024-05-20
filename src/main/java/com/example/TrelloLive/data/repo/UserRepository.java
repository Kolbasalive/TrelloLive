package com.example.TrelloLive.data.repo;

import com.example.TrelloLive.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {}
