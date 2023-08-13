package com.example.test.service.venue;

import com.example.test.dto.CreateVenueDto;
import com.example.test.exception.NotFoundException;
import com.example.test.model.Venue;
import com.example.test.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository repository;

    public VenueServiceImpl(VenueRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(CreateVenueDto data) {
        this.repository.save(new Venue(data.name(), data.location()));
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
