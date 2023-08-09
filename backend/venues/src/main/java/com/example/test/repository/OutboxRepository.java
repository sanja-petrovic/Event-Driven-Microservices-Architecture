package com.example.test.repository;

import com.example.test.model.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxMessage, UUID> {
}
