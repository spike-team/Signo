package kim.jaehoon.studentable.signo.service.changes;

import kim.jaehoon.studentable.signo.domain.entity.Changes;
import kim.jaehoon.studentable.signo.domain.repository.ChangesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChangesServiceImpl implements ChangesService {

    @Autowired
    ChangesRepository changesRepository;

    @Override
    public Mono<Changes> appendChanges(Changes changes) {
        return changesRepository.save(changes);
    }

    @Override
    public Flux<Changes> findAll(String schoolCode, String schoolClass) {
        return changesRepository.findAllBySchoolCodeAndSchoolClass(schoolCode, schoolClass);
    }


}
