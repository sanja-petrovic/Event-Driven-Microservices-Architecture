package eventio.gateway.controller.tickets;

import eventio.gateway.dto.UserIdDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static eventio.gateway.constants.WebClients.ticketClient;

@RequestMapping("/tickets")
@RestController
public class TicketController {
    @GetMapping("/{id}/availability")
    public ResponseEntity<Boolean> checkAvailability(@RequestParam UUID id) {
        Mono<Boolean> response = ticketClient.get().uri(String.format("/%s/availability", id)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        Boolean available = response.block();
        return ResponseEntity.ok(available);
    }

    @PostMapping("/{id}/select")
    public void confirmSelection(@RequestParam UUID id, @RequestBody UserIdDto data) {
        ticketClient.post().uri(String.format("/%s/select", id)).body(BodyInserters.fromValue(data)).retrieve().bodyToMono(String.class).block();
    }

    @PostMapping("/{id}/purchase")
    public void confirmPurchase(@RequestParam UUID id, @RequestBody UserIdDto data) {
        ticketClient.post().uri(String.format("/%s/purchase", id)).body(BodyInserters.fromValue(data)).retrieve().bodyToMono(String.class).block();
    }

    @PostMapping("/{id}/cancel")
    public void confirmPurchase(@RequestParam UUID id) {
        ticketClient.post().uri(String.format("/%s/cancel", id)).retrieve().bodyToMono(String.class).block();
    }
}
