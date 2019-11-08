package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.document.TimetableManager;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TimetableManagerRepository extends ReactiveCrudRepository<TimetableManager, String> {
    Mono<TimetableManager> findByEmail(String email);
}
