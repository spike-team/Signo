package kim.jaehoon.studentable.signo.service.student;

import kim.jaehoon.studentable.signo.domain.payload.StudentForm;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface StudentService {

    Mono updateStudentInfo(StudentForm studentInfo);

}
