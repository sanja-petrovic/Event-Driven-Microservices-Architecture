package com.example.test.service.concert;

import com.example.test.dto.CreateConcertDto;
import com.example.test.exception.NotFoundException;
import com.example.test.model.Concert;
import com.example.test.repository.ConcertRepository;
import com.example.test.util.DateTimeParser;
import com.example.test.util.InputChecker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository repository;

    public ConcertServiceImpl(ConcertRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(CreateConcertDto data) {
        this.repository.save(
                new Concert(data.name(),
                        UUID.fromString(data.venueId()),
                        data.performer(),
                        DateTimeParser.parse(data.dateTime()))
        );
    }

    @Override
    public List<Concert> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Concert findById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<Concert> findAllByVenue(UUID venueId) {
        return this.repository.findAllByVenueId(venueId);
    }

    @Override
    public List<Concert> findAllByPerformer(String performer) {
        return this.repository.findAllByPerformerContainsIgnoreCase(performer);
    }

    @Override
    public List<Concert> findAllByVenueAndPerformer(UUID venueId, String performer) {
        return this.repository.findAllByVenueIdAndPerformerContainsIgnoreCase(venueId, performer);
    }

    @Override
    public List<Concert> search(String venueId, String performer) {
        performer = performer.strip();
        boolean venueIdMissing = InputChecker.isBlankNullOrEmpty(venueId);
        boolean performerMissing = InputChecker.isBlankNullOrEmpty(performer);
        if (venueIdMissing && performerMissing) {
            return this.findAll();
        } else if (venueIdMissing) {
            return this.findAllByPerformer(performer);
        } else if (performerMissing) {
            return this.findAllByVenue(UUID.fromString(venueId));
        } else {
            return this.findAllByVenueAndPerformer(UUID.fromString(venueId), performer);
        }
    }

}
