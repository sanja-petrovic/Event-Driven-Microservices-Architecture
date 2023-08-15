package eventio.auth.service.outbox;

import eventio.auth.model.Account;
import eventio.auth.model.OutboxMessage;

import java.util.UUID;

public interface OutboxService {
    OutboxMessage generate(String type, Account account);
    void send(OutboxMessage message);
}
