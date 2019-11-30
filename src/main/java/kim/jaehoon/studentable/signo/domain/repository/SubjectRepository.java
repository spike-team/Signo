package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.Subject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SubjectRepository extends ReactiveCrudRepository<Subject, String> {

    Flux<Subject> findAllBySchoolCode(String schoolCode);

}
