package eventio.concerts.controller;

import eventio.concerts.dto.ConcertDto;
import eventio.concerts.dto.CreateConcertDto;
import eventio.concerts.model.Concert;
import eventio.concerts.service.concert.ConcertService;
import eventio.concerts.util.DateTimeParser;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/concerts")
public class ConcertController {
    private final ConcertService service;

    public ConcertController(ConcertService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Find concerts. Optionally, filter by venue ID.")
    public ResponseEntity<List<ConcertDto>> findAll(@RequestParam(required = false) String venueId, @RequestParam(required = false) String performer) {
        List<ConcertDto> concerts = this.service.search(venueId, performer).stream()
                .map(concert -> new ConcertDto(concert.getId(), concert.getName(), concert.getPerformer(), concert.getVenueId(), DateTimeParser.format(concert.getDateTime()), concert.getAttendance())).collect(Collectors.toList());
        return ResponseEntity.ok(concerts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find concert by ID.")
    public ResponseEntity<Concert> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.findById(UUID.fromString(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new concert.")
    public ResponseEntity create(@RequestBody CreateConcertDto data) {
        this.service.create(data);
        return ResponseEntity.ok().build();
    }

}
