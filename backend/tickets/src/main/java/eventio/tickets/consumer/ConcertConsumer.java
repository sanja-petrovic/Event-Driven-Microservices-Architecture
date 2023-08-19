package eventio.tickets.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventio.tickets.dto.ConcertDto;
import eventio.tickets.service.tickets.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConcertConsumer {
    private final ObjectMapper objectMapper;
    private final TicketService ticketService;

    @Autowired
    public ConcertConsumer(ObjectMapper objectMapper, TicketService ticketService) {
        this.objectMapper = objectMapper;
        this.ticketService = ticketService;
    }


    @KafkaListener(topics = "outbox.event.concertCreated", groupId = "tickets")
    public void consume(String message, @Header("correlation_id") String correlationID) throws JsonProcessingException {
        ConcertDto concert = objectMapper.readValue(message, ConcertDto.class);
        this.ticketService.generateTickets(concert.venueId(), concert.concertId());
    }

}