package kim.jaehoon.studentable.signo.handler;

import kim.jaehoon.studentable.signo.domain.entity.Teacher;
import kim.jaehoon.studentable.signo.domain.repository.TeacherRepository;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class TeacherHandler {

    private TeacherRepository teacherRepository;

    public TeacherHandler(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Mono<ServerResponse> createTeacher(ServerRequest request) {
        Mono<Teacher> teacherMono = request.bodyToMono(Teacher.class).flatMap(teacher -> teacherRepository.save(teacher));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(teacherMono, Teacher.class);
    }
}
