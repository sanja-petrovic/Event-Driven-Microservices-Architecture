package eventio.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private UUID id;
    private UUID userId;
    private UUID concertId;
    private int seat;
    private String status;
}