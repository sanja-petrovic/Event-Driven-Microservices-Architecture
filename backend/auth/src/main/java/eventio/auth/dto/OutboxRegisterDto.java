package eventio.auth.dto;

import java.util.UUID;

public record OutboxRegisterDto(UUID id, String name, String address, String phone) {
}
