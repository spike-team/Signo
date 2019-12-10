package kim.jaehoon.studentable.signo.exception.school;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "School not found.")
public class SchoolNotFoundException extends RuntimeException {
}
