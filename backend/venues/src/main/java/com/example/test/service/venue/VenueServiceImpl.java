package com.example.test.service.venue;

import com.example.test.dto.CreateVenueDto;
import com.example.test.event.VenueCreatedEvent;
import com.example.test.exception.NotFoundException;
import com.example.test.model.OutboxMessage;
import com.example.test.model.Venue;
import com.example.test.repository.VenueRepository;
import com.example.test.service.outbox.OutboxService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository repository;
    private final OutboxService outboxService;

    public VenueServiceImpl(VenueRepository repository, OutboxService outboxService) {
        this.repository = repository;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public void create(CreateVenueDto data) {
        Venue venue = this.repository.save(new Venue(data.name(), data.location(), data.capacity()));
        OutboxMessage message = this.outboxService.generate("venueCreated", new VenueCreatedEvent(venue.getId(), venue.getCapacity()));
        this.outboxService.save(message);
    }


    @Override
    public List<Venue> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Venue findById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }
}
