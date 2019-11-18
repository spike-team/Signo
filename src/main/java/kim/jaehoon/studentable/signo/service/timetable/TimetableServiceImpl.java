package kim.jaehoon.studentable.signo.service.timetable;

import kim.jaehoon.studentable.signo.domain.document.Timetable;
import kim.jaehoon.studentable.signo.domain.repository.TimetableRepository;
import kim.jaehoon.studentable.signo.exception.TimetableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    TimetableRepository timetableRepository;


    @Override
    public Mono<Timetable> findBySchoolCodeAndSchoolClass(String schoolCode, String schoolClass) {
        return timetableRepository.findBySchoolCodeAndSchoolClass(schoolCode, schoolClass)
                .switchIfEmpty(Mono.error(new TimetableNotFoundException()));
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
