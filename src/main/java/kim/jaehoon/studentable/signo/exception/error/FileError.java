package kim.jaehoon.studentable.signo.exception.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "File error occurred.")
public class FileError extends RuntimeException {

    public FileError(String message) {
        super(message);
    }

    public FileError(String message, Throwable cause) {
        super(message, cause);
    }

}
