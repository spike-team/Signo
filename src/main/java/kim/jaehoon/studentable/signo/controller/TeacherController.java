package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.payload.Teachers;
import kim.jaehoon.studentable.signo.service.teacher.TeacherService;
import kim.jaehoon.studentable.signo.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public Flux createTeachers(@RequestHeader("Authorization") String auth, @RequestBody Teachers teachers) {
        return teacherService.createTeachers(teachers, tokenService.getIdentity(auth));
    }

}
