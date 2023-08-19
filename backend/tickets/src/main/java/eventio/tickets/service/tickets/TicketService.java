package eventio.tickets.service.tickets;

import eventio.tickets.model.Ticket;

import java.util.UUID;

public interface TicketService {
    void generateTickets(UUID venueId, UUID concertId);
    Ticket findById(UUID id);
    boolean checkAvailability(UUID ticketId);
    void confirmSelection(UUID ticketId, UUID userId);
    void confirmPurchase(UUID ticketId, UUID userId);
    void cancel(UUID ticketId);
}
