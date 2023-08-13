package eventio.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnrecognizedAuthority extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnrecognizedAuthority(String authority) {
        super(String.format("Authority %s was not recognized.", authority));
    }
}