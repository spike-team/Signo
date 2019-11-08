package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.document.SchoolInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SchoolInfoRepository extends ReactiveCrudRepository<SchoolInfo, String> {

    Flux<SchoolInfo> findAllByFullNameContaining(String schoolName);
    Mono<SchoolInfo> findBySchoolCode(String schoolCode);
}
