package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TimetableRepository extends ReactiveCrudRepository<Timetable, String> {
    Mono<Timetable> findBySchoolCodeAndGradeAndSchoolClassAndDate(String schoolCode, int grade, int schoolClass, LocalDate date);
}
