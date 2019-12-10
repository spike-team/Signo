package kim.jaehoon.studentable.signo.exception.manager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Manager already exists.")
public class ManagerAlreadyExistsException extends RuntimeException {
}
