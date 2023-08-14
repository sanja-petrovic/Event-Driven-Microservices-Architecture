package eventio.gateway.model;

import java.time.LocalDateTime;
import java.util.UUID;
public record Concert(UUID id, String name, UUID venueId, String performer, LocalDateTime dateTime, int attendance, LocalDateTime created, LocalDateTime lastUpdated) {
}
