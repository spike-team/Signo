package kim.jaehoon.studentable.signo.service.student;

import kim.jaehoon.studentable.signo.domain.payload.StudentForm;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import kim.jaehoon.studentable.signo.domain.repository.StudentRepository;
import kim.jaehoon.studentable.signo.exception.SchoolNotFoundException;
import kim.jaehoon.studentable.signo.exception.student.StudentAlreadyExistsException;
import kim.jaehoon.studentable.signo.exception.student.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private SchoolInfoRepository schoolInfoRepository;

    @Override
    public Mono addStudentInfo(StudentForm studentForm) {
        return studentRepository.findByEmail(studentForm.getEmail())
                .handle((saved, sink) -> sink.error(new StudentAlreadyExistsException()))
                .switchIfEmpty(schoolInfoRepository.findBySchoolCode(studentForm.getSchoolCode())
                        .switchIfEmpty(Mono.error(SchoolNotFoundException::new))
                        .flatMap(school -> studentRepository.save(studentForm.toStudent()))
                );
    }

    @Override
    public Mono updateStudentInfo(StudentForm studentForm) {
        return studentRepository.findByEmail(studentForm.getEmail())
                .switchIfEmpty(Mono.error(StudentNotFoundException::new))
                .flatMap(student -> studentRepository.save(studentForm.toStudentWithId(student.getId())));
    }
}
