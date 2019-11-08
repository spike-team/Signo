package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.document.Teacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeacherRepository extends ReactiveCrudRepository<Teacher, String> {
}
