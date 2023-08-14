package eventio.gateway.controller.venues;

import eventio.gateway.constants.Routes;
import eventio.gateway.model.Venue;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RequestMapping("/venues")
@RestController
public class VenueController {
    private final WebClient venueClient = WebClient.builder().baseUrl(Routes.venuesPath).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultUriVariables(Collections.singletonMap("url", Routes.venuesPath)).build();

    @GetMapping
    @Operation(summary = "Find venues.")
    public ResponseEntity<List<Venue>> findAll() {
        Mono<List<Venue>> response = venueClient.get().uri("/venues").retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        List<Venue> venues = response.block();
        return ResponseEntity.ok(venues);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find venue by ID.")
    public ResponseEntity<Venue> findById(@PathVariable String id) {
        Mono<Venue> response = venueClient.get().uri(String.format("/venues/%s", id)).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
        Venue venue = response.block();
        return ResponseEntity.ok(venue);
    }
}
