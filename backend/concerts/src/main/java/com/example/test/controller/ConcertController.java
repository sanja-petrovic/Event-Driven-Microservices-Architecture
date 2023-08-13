package com.example.test.controller;

import com.example.test.dto.CreateConcertDto;
import com.example.test.model.Concert;
import com.example.test.service.concert.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/concerts")
public class ConcertController {
    private final ConcertService service;

    public ConcertController(ConcertService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Find concerts. Optionally, filter by venue ID.")
    public ResponseEntity<List<Concert>> findAll(@RequestParam(required = false) String venueId, @RequestParam(required = false) String performer) {
        return ResponseEntity.ok(this.service.search(venueId, performer));
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
