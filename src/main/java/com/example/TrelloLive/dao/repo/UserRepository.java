package com.example.TrelloLive.dao.repo;

import com.example.TrelloLive.dao.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {}
