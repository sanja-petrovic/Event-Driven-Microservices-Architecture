package com.example.test.service.outbox;

import com.example.test.event.Event;
import com.example.test.model.OutboxMessage;
import com.example.test.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Service;

@Service
public class OutboxServiceImpl implements OutboxService {
    private final OutboxRepository repository;

    public OutboxServiceImpl(OutboxRepository outboxRepository) {
        this.repository = outboxRepository;
    }

    @Override
    public OutboxMessage generate(String type, Event event) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return new OutboxMessage(type, event.getId(), objectWriter.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void save(OutboxMessage message) {
        this.repository.save(message);
    }
}
