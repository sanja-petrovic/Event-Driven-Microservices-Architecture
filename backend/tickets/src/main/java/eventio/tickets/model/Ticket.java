package eventio.tickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    private UUID id = UUID.randomUUID();
    @Column
    private UUID userId;
    @Column
    private UUID concertId;
    @Column
    private int seat;
    @Column
    @Enumerated(EnumType.STRING)
    private TicketStatus status = TicketStatus.AVAILABLE;
    @Column
    @CreationTimestamp
    private LocalDateTime created;
    @Column
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public Ticket(UUID concertId, int seat) {
        this.concertId = concertId;
        this.seat = seat;
    }
}
