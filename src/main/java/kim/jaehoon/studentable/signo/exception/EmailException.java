package kim.jaehoon.studentable.signo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Email error occurred.")
public class EmailException extends RuntimeException {
}
