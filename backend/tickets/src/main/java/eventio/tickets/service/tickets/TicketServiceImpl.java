package eventio.tickets.service.tickets;

import eventio.tickets.exception.MismatchException;
import eventio.tickets.exception.NotFoundException;
import eventio.tickets.model.Ticket;
import eventio.tickets.model.TicketStatus;
import eventio.tickets.model.Venue;
import eventio.tickets.repository.TicketRepository;
import eventio.tickets.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

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

    @Override
    public Ticket findById(UUID id) {
        return this.ticketRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<Ticket> findAllByConcertId(UUID concertId) {
        return this.ticketRepository.findAllByConcertIdOrderBySeatAsc(concertId);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean checkAvailability(UUID ticketId) {
        return TicketStatus.AVAILABLE.equals(this.findById(ticketId).getStatus());
    }

    @Override
    @Transactional
    public void confirmSelection(UUID ticketId, UUID userId) {
        Ticket ticket = this.findById(ticketId);
        if (TicketStatus.AVAILABLE.equals(ticket.getStatus())) {
            ticket.setUserId(userId);
            ticket.setStatus(TicketStatus.SELECTED);
            this.ticketRepository.save(ticket);
            TimerTask task = new TimerTask() {
                public void run() {
                    try {
                        if (TicketStatus.SELECTED.equals(ticket.getStatus())) {
                            System.out.println("Resetting...");
                            ticket.setStatus(TicketStatus.AVAILABLE);
                            ticket.setUserId(null);
                            ticketRepository.save(ticket);
                        }
                    } catch(Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                }
            };
            Timer timer = new Timer("Timer");
            long delay = TimeUnit.MINUTES.toMillis(1);
            timer.schedule(task, delay);
        }
    }

    @Override
    @Transactional
    public void confirmPurchase(UUID ticketId, UUID userId) {
        Ticket ticket = this.findById(ticketId);
        if (TicketStatus.SELECTED.equals(ticket.getStatus()) && userId.equals(ticket.getUserId())) {
            ticket.setUserId(userId);
            ticket.setStatus(TicketStatus.PURCHASED);
            this.ticketRepository.save(ticket);
        } else {
            throw new MismatchException(ticketId);
        }
    }

    @Override
    @Transactional
    public void cancel(UUID ticketId) {
        Ticket ticket = this.findById(ticketId);
        ticket.setUserId(null);
        ticket.setStatus(TicketStatus.AVAILABLE);
        this.ticketRepository.save(ticket);
    }
}
