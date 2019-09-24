package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.School;
import kim.jaehoon.studentable.signo.domain.Timetable;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TimetableRepository extends CrudRepository<Timetable, String> {
    Mono<Timetable> findBySchoolAndGradeAndSchoolClassAndDate(School school, int grade, int schoolClass, LocalDate date);
}
