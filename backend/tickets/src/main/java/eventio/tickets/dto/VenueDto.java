package eventio.tickets.dto;

import java.util.UUID;

public record VenueDto(UUID venueId, int capacity) {
}
