package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.document.Subject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubjectRepository extends ReactiveCrudRepository<Subject, String> {

    Flux<Subject> findAllBySchoolCode(String schoolCode);

}
