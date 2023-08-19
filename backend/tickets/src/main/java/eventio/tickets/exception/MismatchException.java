package eventio.tickets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
@ResponseStatus(HttpStatus.CONFLICT)
public class MismatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MismatchException(UUID id) {
        super(String.format("Ticket %s purchase unsuccessful: a user can only buy a ticket if it was previously selected by them.", id));
    }
}