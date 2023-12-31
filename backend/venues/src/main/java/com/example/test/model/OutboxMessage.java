package com.example.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    private String aggregateType = "concert";
    @Column
    private UUID aggregateId;
    @Column
    private String type;
    @Column
    @CreationTimestamp
    private LocalDateTime timestamp;
    @Column
    private String correlationId = String.valueOf(UUID.randomUUID());
    @Column(columnDefinition = "TEXT")
    private String payload;

    public OutboxMessage(String type, UUID aggregateId, String payload) {
        this.type = type;
        this.aggregateId = aggregateId;
        this.payload = payload;
    }
}
