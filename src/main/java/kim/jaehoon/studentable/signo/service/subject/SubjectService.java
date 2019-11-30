package kim.jaehoon.studentable.signo.service.subject;

import kim.jaehoon.studentable.signo.domain.document.Subject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface SubjectService {

    Mono<Subject> save(Subject subject);

    Flux<Subject> saveAll(List<Subject> subjects);

    Mono<Subject> findBySubjectId(String subjectId);

    Flux<Subject> findBySchoolCode(String schoolCode);

}
