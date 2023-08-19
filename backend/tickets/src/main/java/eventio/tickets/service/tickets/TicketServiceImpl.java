package eventio.tickets.service.tickets;

import eventio.tickets.exception.NotFoundException;
import eventio.tickets.model.Ticket;
import eventio.tickets.model.Venue;
import eventio.tickets.repository.TicketRepository;
import eventio.tickets.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {
    private final VenueRepository venueRepository;
    private final TicketRepository ticketRepository;


    public TicketServiceImpl(VenueRepository venueRepository, TicketRepository ticketRepository) {
        this.venueRepository = venueRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void generateTickets(UUID venueId, UUID concertId) {
        Venue venue = this.venueRepository.findById(venueId).orElseThrow(() -> new NotFoundException(venueId));
        for (int i = 1; i <= venue.getCapacity(); i++) {
            Ticket ticket = new Ticket(concertId, i);
            this.ticketRepository.save(ticket);
        }
    }
}
