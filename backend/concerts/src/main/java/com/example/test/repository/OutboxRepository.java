package com.example.test.repository;

import com.example.test.model.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxMessage, UUID> {
}
