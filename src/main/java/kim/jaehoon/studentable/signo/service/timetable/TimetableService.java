package kim.jaehoon.studentable.signo.service.timetable;

import kim.jaehoon.studentable.signo.domain.document.Timetable;
import kim.jaehoon.studentable.signo.domain.payload.MappedTimetable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TimetableService {

    Mono<MappedTimetable> findBySchoolCodeAndSchoolClass(String schoolCode, String schoolClass);

    Mono<Timetable> insertTimetable(Timetable timetable);

    Flux<Timetable> findAll();

}
