package kim.jaehoon.studentable.signo.service.school;

import kim.jaehoon.studentable.signo.domain.document.SchoolInfo;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolInfoRepository schoolInfoRepository;

    public SchoolServiceImpl(SchoolInfoRepository schoolInfoRepository) {
        this.schoolInfoRepository = schoolInfoRepository;
    }

    @Override
    public Flux<SchoolInfo> findAllByName(String name) {
        return schoolInfoRepository.findAllByFullNameContaining(name);
    }

    @Override
    public Flux<SchoolInfo> findAll() {
        return schoolInfoRepository.findAll();
    }
}
