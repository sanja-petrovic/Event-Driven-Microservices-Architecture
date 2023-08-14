package eventio.gateway.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Venue(UUID id, String name, String location, LocalDateTime created, LocalDateTime lastUpdated) {
}
