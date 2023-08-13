package com.example.test.repository;

import com.example.test.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, UUID> {
    List<Concert> findAllByVenueId(UUID venueId);
    List<Concert> findAllByPerformerContainsIgnoreCase(String performer);
    List<Concert> findAllByVenueIdAndPerformerContainsIgnoreCase(UUID venueId, String performer);
}
