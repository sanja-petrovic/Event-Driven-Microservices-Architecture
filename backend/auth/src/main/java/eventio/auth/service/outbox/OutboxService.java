package eventio.auth.service.outbox;

import eventio.auth.dto.OutboxRegisterDto;
import eventio.auth.model.Account;
import eventio.auth.model.OutboxMessage;

import java.util.UUID;

public interface OutboxService {
    OutboxMessage generate(String type, OutboxRegisterDto dto);
    void send(OutboxMessage message);
}
