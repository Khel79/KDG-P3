package be.kdg.prog3.upvote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VoteNotFoundException extends RuntimeException {
    public VoteNotFoundException(String msg) {
        super(msg);
    }
}
