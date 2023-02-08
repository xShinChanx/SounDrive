package app.buisness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidSongException extends ResponseStatusException {
    public InvalidSongException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}