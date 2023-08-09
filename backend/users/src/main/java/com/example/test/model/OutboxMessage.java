package com.example.test.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "outbox")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OutboxMessage {
    @Id
    private UUID id = UUID.randomUUID();
    @Column
    private String aggregateType;
    @Column
    private String aggregateId;
    @Column
    private String type;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;
    @Column
    private String correlationId;
    @Column
    private String payload;

    @Builder
    public OutboxMessage(String aggregateType, String aggregateId, String type, LocalDateTime timestamp, String correlationId, String payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.type = type;
        this.timestamp = timestamp;
        this.correlationId = correlationId;
        this.payload = payload;
    }
}
