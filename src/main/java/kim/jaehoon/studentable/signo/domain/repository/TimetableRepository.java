package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.School;
import kim.jaehoon.studentable.signo.domain.Timetable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TimetableRepository extends ReactiveCrudRepository<Timetable, String> {
    Mono<Timetable> findBySchoolCodeAndGradeAndSchoolClassAndDate(String schoolCode, int grade, int schoolClass, LocalDate date);
}
