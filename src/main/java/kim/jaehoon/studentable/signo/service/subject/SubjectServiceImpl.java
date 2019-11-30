package kim.jaehoon.studentable.signo.service.subject;

import kim.jaehoon.studentable.signo.domain.entity.Subject;
import kim.jaehoon.studentable.signo.domain.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Mono<Subject> save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Flux<Subject> saveAll(List<Subject> subjects) {
        return subjectRepository.saveAll(subjects);
    }

    @Override
    public Mono<Subject> findBySubjectId(String subjectId) {
        return subjectRepository.findById(subjectId);
    }

    @Override
    public Flux<Subject> findBySchoolCode(String schoolCode) {
        return subjectRepository.findAllBySchoolCode(schoolCode);
    }
}
