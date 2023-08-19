package eventio.tickets.service.tickets;

import java.util.UUID;

public interface TicketService {
    void generateTickets(UUID venueId, UUID concertId);
}
