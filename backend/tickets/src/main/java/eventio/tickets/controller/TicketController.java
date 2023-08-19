package eventio.tickets.controller;


import eventio.tickets.dto.UserIdDto;
import eventio.tickets.service.tickets.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Boolean> checkAvailability(@RequestParam UUID id) {
        return ResponseEntity.ok(this.ticketService.checkAvailability(id));
    }

    @PostMapping("/{id}/select")
    public void confirmSelection(@RequestParam UUID id, @RequestBody UserIdDto data) {
        this.ticketService.confirmSelection(id, data.id());
    }

    @PostMapping("/{id}/purchase")
    public void confirmPurchase(@RequestParam UUID id, @RequestBody UserIdDto data) {
        this.ticketService.confirmPurchase(id, data.id());
    }

    @PostMapping("/{id}/cancel")
    public void confirmPurchase(@RequestParam UUID id) {
        this.ticketService.cancel(id);
    }
}
