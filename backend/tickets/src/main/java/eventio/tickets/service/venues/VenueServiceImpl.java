package eventio.tickets.service.venues;

import eventio.tickets.model.Venue;
import eventio.tickets.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository repository;

    public VenueServiceImpl(VenueRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(UUID id, int capacity) {
        this.repository.save(new Venue(id, capacity));
    }
}
