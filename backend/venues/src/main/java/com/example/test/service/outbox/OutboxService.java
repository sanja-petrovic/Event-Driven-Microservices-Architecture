package com.example.test.service.outbox;

import com.example.test.model.OutboxMessage;

public interface OutboxService {
    void save(OutboxMessage message);
}
