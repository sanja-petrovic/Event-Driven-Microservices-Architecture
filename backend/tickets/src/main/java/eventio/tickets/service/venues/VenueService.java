package eventio.tickets.service.venues;

import java.util.UUID;

public interface VenueService {
    void save(UUID id, int capacity);
}
