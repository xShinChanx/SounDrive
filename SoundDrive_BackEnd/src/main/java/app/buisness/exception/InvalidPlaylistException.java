package app.buisness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPlaylistException extends ResponseStatusException {

    public InvalidPlaylistException (String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
