package kim.jaehoon.studentable.signo.exception.manager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Login information is incorrect")
public class InvalidUserCredentialException extends RuntimeException {}
