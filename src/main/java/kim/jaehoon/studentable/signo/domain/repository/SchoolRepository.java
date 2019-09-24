package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.School;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SchoolRepository extends CrudRepository<School, String> {

    Mono<School> findByName(String schoolName);
    Flux<School> findAllByName(String schoolName);
}
