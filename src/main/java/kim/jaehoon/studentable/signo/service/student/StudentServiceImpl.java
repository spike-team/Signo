package kim.jaehoon.studentable.signo.service.student;

import kim.jaehoon.studentable.signo.domain.payload.StudentForm;
import kim.jaehoon.studentable.signo.domain.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    public Mono updateStudentInfo(StudentForm studentForm) {
        return studentRepository.findByEmail(studentForm.getEmail())
                .switchIfEmpty(studentRepository.save(studentForm.toStudent()))
                .flatMap(student -> studentRepository.save(studentForm.toStudentWithId(student.getId())));
    }
}
