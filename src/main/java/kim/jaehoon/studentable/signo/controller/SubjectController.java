package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.document.Subject;
import kim.jaehoon.studentable.signo.domain.payload.PreSubject;
import kim.jaehoon.studentable.signo.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/subject")
    public Mono getBySubjectId(@RequestParam String subjectId) {
        return subjectService.findBySubjectId(subjectId);
    }

    @GetMapping("/subjects")
    public Flux fetBySchoolCode(@RequestParam String schoolCode) {
        return subjectService.findBySchoolCode(schoolCode);
    }

    @PostMapping("/subject")
    public Mono addSubject(@RequestBody PreSubject preSubject) {
        return subjectService.save(preSubject.toSubject());
    }

    @PostMapping("/subjects")
    public Flux addSubjects(@RequestBody List<PreSubject> preSubjects) {
        List<Subject> subjects = new ArrayList<>();

        for (PreSubject pre : preSubjects)
            subjects.add(pre.toSubject());

        return subjectService.saveAll(subjects);
    }

}
