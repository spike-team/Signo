package kim.jaehoon.studentable.signo.service.changes;

import kim.jaehoon.studentable.signo.domain.entity.Changes;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ChangesService {

    Mono<Changes> appendChanges(Changes changes);

    Flux<Changes> findAll(String schoolCode, String schoolClass);

}
