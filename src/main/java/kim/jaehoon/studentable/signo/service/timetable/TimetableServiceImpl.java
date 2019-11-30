package kim.jaehoon.studentable.signo.service.timetable;

import kim.jaehoon.studentable.signo.domain.entity.Subject;
import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import kim.jaehoon.studentable.signo.domain.payload.MappedTimetable;
import kim.jaehoon.studentable.signo.domain.repository.SubjectRepository;
import kim.jaehoon.studentable.signo.domain.repository.TimetableRepository;
import kim.jaehoon.studentable.signo.exception.TimetableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    TimetableRepository timetableRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Mono<MappedTimetable> findBySchoolCodeAndSchoolClass(String schoolCode, String schoolClass) {
        return timetableRepository.findBySchoolCodeAndSchoolClass(schoolCode, schoolClass)
                .switchIfEmpty(Mono.error(new TimetableNotFoundException()))
                .flatMap(timetable -> {
                    List<String> subjects = timetable.getSubjects();
                    List<Mono<Subject>> mappedSubjects = new ArrayList<>();
                    for (String subject : subjects) {
                        mappedSubjects.add(subjectRepository.findById(subject));
                    }
                    MappedTimetable mappedTimetable = new MappedTimetable(timetable);
                    mappedTimetable.setSubjects(mappedSubjects);
                    return Mono.just(mappedTimetable);
                });
    }

    @Override
    public Mono<Timetable> insertTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    @Override
    public Flux<Timetable> findAll() {
        return timetableRepository.findAll();
    }

}
