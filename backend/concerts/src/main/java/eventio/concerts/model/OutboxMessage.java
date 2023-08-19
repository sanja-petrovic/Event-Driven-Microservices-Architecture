package eventio.concerts.model;

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
