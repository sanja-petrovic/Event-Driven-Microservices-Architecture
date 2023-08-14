package eventio.gateway.controller.concerts;

import eventio.gateway.constants.Routes;
import eventio.gateway.dto.CreateConcertDto;
import eventio.gateway.model.Concert;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RequestMapping("/concerts")
@RestController
public class ConcertController {

    private final WebClient concertClient =  WebClient.builder()
            .baseUrl(Routes.concertsPath)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", Routes.concertsPath))
            .build();

    @GetMapping
    @Operation(summary = "Find concerts. Optionally, filter by venue ID.")
    public ResponseEntity<List<Concert>> findAll(@RequestParam(required = false) String venueId, @RequestParam(required = false) String performer) {
        venueId = venueId == null ? "" : venueId;
        performer = performer == null ? "" : performer;
        Mono<List<Concert>> response = concertClient.get().uri(String.format("/concerts?venueId=%s&performer=%s", venueId, performer)).retrieve().bodyToMono(new ParameterizedTypeReference<List<Concert>>() {
        });
        List<Concert> concerts = response.block();
        return ResponseEntity.ok(concerts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find concert by ID.")
    public ResponseEntity<Concert> findById(@PathVariable String id) {
        Mono<Concert> response = concertClient.get().uri(String.format("/concerts/%s", id)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        Concert concert = response.block();
        return ResponseEntity.ok(concert);
    }
}
