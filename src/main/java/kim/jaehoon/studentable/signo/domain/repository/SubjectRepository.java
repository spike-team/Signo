package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.School;
import kim.jaehoon.studentable.signo.domain.Subject;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

public interface SubjectRepository extends CrudRepository<Subject, String> {

    Mono<Subject> findBySchool(School school);
}
