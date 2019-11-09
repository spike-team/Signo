package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.document.SchoolInfo;
import kim.jaehoon.studentable.signo.service.school.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    public Flux<SchoolInfo> findAll() {
        return schoolService.findAll();
    }

    @GetMapping("/school")
    public Flux<SchoolInfo> searchSchoolId(@RequestParam String key) {
        return schoolService.findAllByName(key);
    }
}
