package eventio.gateway.controller.tickets;

import eventio.gateway.dto.UserIdDto;
import eventio.gateway.model.Ticket;
import eventio.gateway.model.Venue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static eventio.gateway.constants.WebClients.ticketClient;
import static eventio.gateway.constants.WebClients.venueClient;

@RequestMapping("/tickets")
@RestController
public class TicketController {
    @GetMapping("/{id}/availability")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable UUID id) {
        Mono<Boolean> response = ticketClient.get().uri(String.format("/tickets/%s/availability", id)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        Boolean available = response.block();
        return ResponseEntity.ok(available);
    }

    @GetMapping("/concerts/{concertId}")
    public ResponseEntity<List<Ticket>> findAllByConcert(@PathVariable UUID concertId) {
        Mono<List<Ticket>> response = ticketClient.get().uri(String.format("/tickets/concerts/%s", concertId)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        List<Ticket> tickets = response.block();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/{id}/select")
    public void confirmSelection(@PathVariable UUID id, @RequestBody UserIdDto data) {
        ticketClient.post().uri(String.format("/tickets/%s/select", id)).body(BodyInserters.fromValue(data)).retrieve().bodyToMono(String.class).block();
    }

    @PostMapping("/{id}/purchase")
    public void confirmPurchase(@PathVariable UUID id, @RequestBody UserIdDto data) {
        ticketClient.post().uri(String.format("/tickets/%s/purchase", id)).body(BodyInserters.fromValue(data)).retrieve().bodyToMono(String.class).block();
    }

    @PostMapping("/{id}/cancel")
    public void confirmPurchase(@PathVariable UUID id) {
        ticketClient.post().uri(String.format("/tickets/%s/cancel", id)).retrieve().bodyToMono(String.class).block();
    }
}
