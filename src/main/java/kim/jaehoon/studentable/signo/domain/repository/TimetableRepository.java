package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.document.Timetable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TimetableRepository extends ReactiveCrudRepository<Timetable, String> {
    Mono<Timetable> findBySchoolCodeAndSchoolClassAndDate(String schoolCode, int schoolClass, LocalDate date);
}
