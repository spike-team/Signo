package kim.jaehoon.studentable.signo.service.school;

import kim.jaehoon.studentable.signo.domain.entity.SchoolInfo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface SchoolService {
    Flux<SchoolInfo> findAllByName(String name);
    Flux<SchoolInfo> findAll();
}
