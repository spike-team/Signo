package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.payload.StudentForm;
import kim.jaehoon.studentable.signo.service.student.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PutMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public Mono updateStudentInfo(@RequestBody StudentForm studentForm) {
        return studentService.updateStudentInfo(studentForm);
    }

}
