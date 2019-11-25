package kim.jaehoon.studentable.signo.service.teacher;

import kim.jaehoon.studentable.signo.domain.document.Teacher;
import kim.jaehoon.studentable.signo.domain.payload.TeacherInfo;
import kim.jaehoon.studentable.signo.domain.payload.Teachers;
import kim.jaehoon.studentable.signo.domain.repository.TeacherRepository;
import kim.jaehoon.studentable.signo.domain.repository.ManagerRepository;
import kim.jaehoon.studentable.signo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public Flux createTeachers(Teachers teacherInfos, String identity) {

        return managerRepository.findByEmail(identity)
                .switchIfEmpty(Mono.error(new UserNotFoundException()))
                .flatMapMany(timetableManager -> teacherRepository.saveAll(convertToTeacherFlux(teacherInfos.getTeachers(), timetableManager.getSchoolCode())));

    }

    @Override
    public Mono<Teacher> updateTeacher() {
        return null;
    }

    @Override
    public Mono<Teacher> deleteTeacher() {
        return null;
    }

    @Override
    public Flux<Teacher> getTeachers() {
        return null;
    }

    private Flux<Teacher> convertToTeacherFlux(List<TeacherInfo> teacherInfos, String schoolCode) {

        List<Teacher> results = new ArrayList<>();

        teacherInfos.forEach(teacher -> results.add(teacher.toTeacher(schoolCode)));

        return Flux.fromIterable(results);

    }

}
