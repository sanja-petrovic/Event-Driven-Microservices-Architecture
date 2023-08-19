package eventio.tickets.dto;

import java.util.UUID;

public record ConcertDto(UUID concertId, UUID venueId) {
}
