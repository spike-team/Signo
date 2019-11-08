package kim.jaehoon.studentable.signo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "School error occurred.")
public class SchoolError extends RuntimeException {

    public SchoolError(String message) {
        super(message);
    }

    public SchoolError(String message, Throwable cause) {
        super(message, cause);
    }
}
