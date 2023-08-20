package eventio.tickets.controller;


import eventio.tickets.dto.UserIdDto;
import eventio.tickets.model.Ticket;
import eventio.tickets.service.tickets.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/concerts/{concertId}")
    public ResponseEntity<List<Ticket>> findAllByConcert(@PathVariable UUID concertId) {
        return ResponseEntity.ok(this.ticketService.findAllByConcertId(concertId));
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable UUID id) {
        return ResponseEntity.ok(this.ticketService.checkAvailability(id));
    }

    @PostMapping("/{id}/select")
    public void confirmSelection(@PathVariable UUID id, @RequestBody UserIdDto data) {
        this.ticketService.confirmSelection(id, data.id());
    }

    @PostMapping("/{id}/purchase")
    public void confirmPurchase(@PathVariable UUID id, @RequestBody UserIdDto data) {
        this.ticketService.confirmPurchase(id, data.id());
    }

    @PostMapping("/{id}/cancel")
    public void confirmPurchase(@PathVariable UUID id) {
        this.ticketService.cancel(id);
    }
}
