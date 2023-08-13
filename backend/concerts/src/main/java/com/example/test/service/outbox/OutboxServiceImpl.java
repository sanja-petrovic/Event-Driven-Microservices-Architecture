package com.example.test.service.outbox;

import com.example.test.model.OutboxMessage;
import com.example.test.repository.OutboxRepository;
import org.springframework.stereotype.Service;

@Service
public class OutboxServiceImpl implements OutboxService {
    private final OutboxRepository repository;

    public OutboxServiceImpl(OutboxRepository outboxRepository) {
        this.repository = outboxRepository;
    }

    @Override
    public void save(OutboxMessage message) {
        this.repository.save(message);
    }
}
