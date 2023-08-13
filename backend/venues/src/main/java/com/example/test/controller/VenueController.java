package com.example.test.controller;

import com.example.test.dto.CreateVenueDto;
import com.example.test.model.Venue;
import com.example.test.service.venue.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/venues")
@RestController
public class VenueController {
    private final VenueService service;

    public VenueController(VenueService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Find all venues.")
    public ResponseEntity<List<Venue>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find venue by ID.")
    public ResponseEntity<Venue> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.findById(UUID.fromString(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new venue.")
    public ResponseEntity create(@RequestBody CreateVenueDto data) {
        this.service.create(data);
        return ResponseEntity.ok().build();
    }

}
