package kim.jaehoon.studentable.signo.exception.timetable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Timetable not found.")
public class TimetableNotFoundException extends RuntimeException {
}
