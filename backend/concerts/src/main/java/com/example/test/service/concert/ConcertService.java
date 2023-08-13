package com.example.test.service.concert;

import com.example.test.dto.CreateConcertDto;
import com.example.test.model.Concert;

import java.util.List;
import java.util.UUID;

public interface ConcertService {
    void create(CreateConcertDto data);
    List<Concert> findAll();
    Concert findById(UUID id);
    List<Concert> findAllByVenue(UUID venueId);
    List<Concert> findAllByPerformer(String performer);
    List<Concert> findAllByVenueAndPerformer(UUID venueId, String performer);
    List<Concert> search(String venueId, String performer);

}
