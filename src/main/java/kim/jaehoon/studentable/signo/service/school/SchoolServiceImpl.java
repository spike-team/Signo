package kim.jaehoon.studentable.signo.service.school;

import kim.jaehoon.studentable.signo.domain.entity.SchoolInfo;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import kim.jaehoon.studentable.signo.exception.school.SchoolNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolInfoRepository schoolInfoRepository;

    public SchoolServiceImpl(SchoolInfoRepository schoolInfoRepository) {
        this.schoolInfoRepository = schoolInfoRepository;
    }

    @Override
    public Flux<SchoolInfo> findAllByName(String name) {
        return schoolInfoRepository.findAllByFullNameContaining(name)
                .switchIfEmpty(Mono.error(new SchoolNotFoundException()));
    }

    @Override
    public Flux<SchoolInfo> findAll() {
        return schoolInfoRepository.findAll();
    }
}
