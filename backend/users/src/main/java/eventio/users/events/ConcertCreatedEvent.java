package eventio.users.events;

import java.util.UUID;

public record ConcertCreatedEvent(UUID id, UUID venueId) {
}
