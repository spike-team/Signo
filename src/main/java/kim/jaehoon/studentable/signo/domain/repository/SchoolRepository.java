package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.School;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SchoolRepository extends ReactiveCrudRepository<School, String> {

    Mono<School> findBySchoolCode(String schoolCode);
    Flux<School> findAllByName(String schoolCode);
}
