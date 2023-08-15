package eventio.users.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventio.users.model.Profile;
import eventio.users.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OutboxConsumer {

    private final ObjectMapper objectMapper;
    private final ProfileService profileService;
    @Autowired
    public OutboxConsumer(ObjectMapper objectMapper, ProfileService profileService) {
        this.objectMapper = objectMapper;
        this.profileService = profileService;
    }


    @KafkaListener(topics = "outbox.event.register", groupId = "users")
    public void consumeMessage(String message,
                               @Header("correlation_id") String correlationID) throws JsonProcessingException {
        System.out.println(message);
        Profile profile = objectMapper.readValue(message, Profile.class);
        this.profileService.register(profile);
    }

}