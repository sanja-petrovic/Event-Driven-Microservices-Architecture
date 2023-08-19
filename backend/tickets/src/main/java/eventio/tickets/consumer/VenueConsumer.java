package eventio.tickets.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventio.tickets.dto.ConcertDto;
import eventio.tickets.dto.VenueDto;
import eventio.tickets.service.venues.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VenueConsumer {
    private final ObjectMapper objectMapper;
    private final VenueService venueService;

    public VenueConsumer(ObjectMapper objectMapper, VenueService venueService) {
        this.objectMapper = objectMapper;
        this.venueService = venueService;
    }

    @KafkaListener(topics = "outbox.event.venueCreated", groupId = "tickets")
    public void consume(String message, @Header("correlation_id") String correlationID) throws JsonProcessingException {
        ConcertDto concert = objectMapper.readValue(message, ConcertDto.class);
        VenueDto venueDto = objectMapper.readValue(message, VenueDto.class);
        this.venueService.save(venueDto.venueId(), venueDto.capacity());
    }

}