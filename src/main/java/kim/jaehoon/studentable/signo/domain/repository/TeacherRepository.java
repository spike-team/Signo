package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.Teacher;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

public interface TeacherRepository extends CrudRepository<Teacher, String> {

    Mono<Teacher> findByEmail(String email);
}
