package eventio.concerts.dto;

public record CreateConcertDto(String name, String venueId, String performer, String dateTime) {
}
