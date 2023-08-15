package eventio.auth.model;

import jakarta.persistence.*;
import lombok.*;
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
    private String aggregateType = "auth";
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

    @Builder
    public OutboxMessage(String aggregateType, UUID aggregateId, String type, LocalDateTime timestamp, String correlationId, String payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.type = type;
        this.timestamp = timestamp;
        this.correlationId = correlationId;
        this.payload = payload;
    }

    public OutboxMessage(String type, UUID aggregateId, String payload) {
        this.type = type;
        this.aggregateId = aggregateId;
        this.payload = payload;
    }
}
