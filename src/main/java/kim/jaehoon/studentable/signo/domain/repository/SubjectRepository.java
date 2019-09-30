package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.School;
import kim.jaehoon.studentable.signo.domain.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SubjectRepository extends ReactiveCrudRepository<Subject, String> {

    Mono<Subject> findBySchoolCode(String schoolCode);
}
