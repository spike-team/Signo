package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.document.Subject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SubjectRepository extends ReactiveCrudRepository<Subject, String> {

    Mono<Subject> findBySchoolCode(String schoolCode);
}
