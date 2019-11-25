package kim.jaehoon.studentable.signo.service.teacher;

import kim.jaehoon.studentable.signo.domain.document.Teacher;
import kim.jaehoon.studentable.signo.domain.payload.TeacherInfo;
import kim.jaehoon.studentable.signo.domain.payload.Teachers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface TeacherService {

    Flux createTeachers(Teachers teachers, String schoolCode);

    Mono<Teacher> updateTeacher();

    Mono<Teacher> deleteTeacher();

    Flux<Teacher> getTeachers();

}