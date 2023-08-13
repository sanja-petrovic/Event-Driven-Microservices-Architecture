package com.example.test.service.venue;

import com.example.test.dto.CreateVenueDto;
import com.example.test.model.Venue;

import java.util.List;
import java.util.UUID;

public interface VenueService {
    void create(CreateVenueDto data);
    List<Venue> findAll();
    Venue findById(UUID id);

}
