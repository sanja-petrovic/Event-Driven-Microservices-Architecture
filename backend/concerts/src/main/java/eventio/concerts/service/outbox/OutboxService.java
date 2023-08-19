package eventio.concerts.service.outbox;

import eventio.concerts.event.Event;
import eventio.concerts.model.OutboxMessage;

public interface OutboxService {
    OutboxMessage generate(String type, Event event);
    void save(OutboxMessage message);
}
