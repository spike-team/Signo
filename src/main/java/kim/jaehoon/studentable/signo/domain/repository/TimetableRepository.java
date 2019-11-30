package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TimetableRepository extends ReactiveCrudRepository<Timetable, String> {

    Mono<Timetable> findBySchoolCodeAndSchoolClass(String schoolCode, String schoolClass);

    Mono<Timetable> findBySchoolCode(String schoolCode);

}
