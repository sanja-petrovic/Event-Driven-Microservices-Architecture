package eventio.auth.service.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import eventio.auth.model.Account;
import eventio.auth.model.OutboxMessage;
import eventio.auth.repository.OutboxRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OutboxServiceImpl implements OutboxService {
    private final OutboxRepository repository;

    public OutboxServiceImpl(OutboxRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutboxMessage generate(String type, Account account) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return new OutboxMessage(type, account.getId(), objectWriter.writeValueAsString(account));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(OutboxMessage message) {
        this.repository.save(message);
    }
}
