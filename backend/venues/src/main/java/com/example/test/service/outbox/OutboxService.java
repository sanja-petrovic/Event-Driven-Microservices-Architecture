package com.example.test.service.outbox;

import com.example.test.event.Event;
import com.example.test.model.OutboxMessage;

public interface OutboxService {
    OutboxMessage generate(String type, Event event);

    void save(OutboxMessage message);
}
