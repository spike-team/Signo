package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.Changes;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ChangesRepository extends ReactiveMongoRepository<Changes, Long> {

    Flux<Changes> findAllBySchoolCodeAndSchoolClass(String schoolCode, String schoolClass);

}
