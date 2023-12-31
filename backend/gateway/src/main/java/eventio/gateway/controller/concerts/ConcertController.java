package eventio.gateway.controller.concerts;

import eventio.gateway.dto.ConcertDto;
import eventio.gateway.model.Concert;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static eventio.gateway.constants.WebClients.concertClient;

@RequestMapping("/concerts")
@RestController
public class ConcertController {


    @GetMapping
    @Operation(summary = "Find concerts. Optionally, filter by venue ID.")
    public ResponseEntity<List<ConcertDto>> findAll(@RequestParam(required = false) String venueId, @RequestParam(required = false) String performer) {
        venueId = venueId == null ? "" : venueId;
        performer = performer == null ? "" : performer;
        Mono<List<ConcertDto>> response = concertClient.get().uri(String.format("/concerts?venueId=%s&performer=%s", venueId, performer)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        List<ConcertDto> concerts = response.block();
        return ResponseEntity.ok(concerts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find concert by ID.")
    public ResponseEntity<ConcertDto> findById(@PathVariable String id) {
        Mono<ConcertDto> response = concertClient.get().uri(String.format("/concerts/%s", id)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        ConcertDto concert = response.block();
        return ResponseEntity.ok(concert);
    }
}
