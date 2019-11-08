package kim.jaehoon.studentable.signo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Region not found.")
public class RegionNotFoundException extends RuntimeException {
}
