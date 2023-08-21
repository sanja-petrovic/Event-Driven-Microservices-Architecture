package eventio.gateway.controller.tickets;

import eventio.gateway.dto.UserIdDto;
import eventio.gateway.model.Ticket;
import eventio.gateway.model.Venue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.*;

import static eventio.gateway.constants.WebClients.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable UUID id) {
        Mono<Ticket> response = ticketClient.get().uri(String.format("/tickets/%s", id)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        Ticket ticket = response.block();
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/{id}/select")
    public void confirmSelection(@PathVariable UUID id, HttpServletRequest request) {
        Mono<UserIdDto> currentMono = authClient.get().uri("/auth/current").header("Authorization", request.getHeader("Authorization")).header("Cookie", request.getHeader("Cookie")).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        UserIdDto current = currentMono.block();
        ticketClient.post().uri(String.format("/tickets/%s/select", id)).body(BodyInserters.fromValue(current)).retrieve().bodyToMono(String.class).block();
    }

    @PostMapping("/{id}/purchase")
    public void confirmPurchase(@PathVariable UUID id, HttpServletRequest request) {
        Mono<UserIdDto> currentMono = authClient.get().uri("/auth/current").header("Authorization", request.getHeader("Authorization")).header("Cookie", request.getHeader("Cookie")).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        UserIdDto current = currentMono.block();
        ticketClient.post().uri(String.format("/tickets/%s/purchase", id)).body(BodyInserters.fromValue(current)).retrieve().bodyToMono(String.class).block();
    }

    @PostMapping("/{id}/cancel")
    public void cancelPurchase(@PathVariable UUID id) {
        ticketClient.post().uri(String.format("/tickets/%s/cancel", id)).retrieve().bodyToMono(String.class).block();
    }
}
