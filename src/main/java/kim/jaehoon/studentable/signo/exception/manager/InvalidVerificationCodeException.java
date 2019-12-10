package kim.jaehoon.studentable.signo.exception.manager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid verification key.")
public class InvalidVerificationCodeException extends RuntimeException {
}
