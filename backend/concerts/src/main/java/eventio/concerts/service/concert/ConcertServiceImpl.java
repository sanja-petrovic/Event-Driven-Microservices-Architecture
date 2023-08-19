package eventio.concerts.service.concert;

import eventio.concerts.dto.CreateConcertDto;
import eventio.concerts.event.ConcertCreatedEvent;
import eventio.concerts.event.Event;
import eventio.concerts.exception.NotFoundException;
import eventio.concerts.model.Concert;
import eventio.concerts.model.OutboxMessage;
import eventio.concerts.repository.ConcertRepository;
import eventio.concerts.service.outbox.OutboxService;
import eventio.concerts.util.DateTimeParser;
import eventio.concerts.util.InputChecker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository repository;
    private final OutboxService outboxService;

    public ConcertServiceImpl(ConcertRepository repository, OutboxService outboxService) {
        this.repository = repository;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public void create(CreateConcertDto data) {
        Concert concert = this.repository.save(new Concert(data.name(), UUID.fromString(data.venueId()), data.performer(), DateTimeParser.parse(data.dateTime())));
        Event event = new ConcertCreatedEvent(
                concert.getId(), concert.getVenueId());
        OutboxMessage outboxMessage = this.outboxService.generate("concertCreated", event);
        this.outboxService.save(outboxMessage);
    }

    @Override
    public List<Concert> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Concert findById(UUID id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
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
        boolean venueIdMissing = InputChecker.isBlankNullOrEmpty(venueId);
        boolean performerMissing = InputChecker.isBlankNullOrEmpty(performer);
        if (venueIdMissing && performerMissing) {
            return this.findAll();
        } else if (venueIdMissing) {
            performer = performer.strip();
            return this.findAllByPerformer(performer);
        } else if (performerMissing) {
            return this.findAllByVenue(UUID.fromString(venueId));
        } else {
            performer = performer.strip();
            return this.findAllByVenueAndPerformer(UUID.fromString(venueId), performer);
        }
    }

}
