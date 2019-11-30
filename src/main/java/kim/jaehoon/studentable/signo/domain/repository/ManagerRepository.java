package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.Manager;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ManagerRepository extends ReactiveCrudRepository<Manager, String> {

    Mono<Manager> findByEmail(String email);

    Mono<Manager> findByEmailAndEmailVerified(String email, boolean verified);

    Mono<Manager> findByVerificationCode(String code);

}
