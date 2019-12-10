package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.Manager;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ManagerRepository extends ReactiveMongoRepository<Manager, String> {

    Mono<Manager> findByEmail(String email);

    Mono<Manager> findByEmailAndEmailVerified(String email, boolean verified);

    Mono<Manager> findByVerificationCode(String code);

}
