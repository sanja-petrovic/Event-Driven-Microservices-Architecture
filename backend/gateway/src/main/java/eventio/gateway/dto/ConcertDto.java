package eventio.gateway.dto;

import java.util.UUID;

public record ConcertDto(UUID id, String name, String performer, UUID venueId, String dateTime, int attendance) {
}