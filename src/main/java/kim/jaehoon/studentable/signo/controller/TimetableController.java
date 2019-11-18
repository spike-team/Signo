package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.document.Timetable;
import kim.jaehoon.studentable.signo.domain.repository.TimetableRepository;
import kim.jaehoon.studentable.signo.service.timetable.TimetableService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TimetableController {

    @Autowired
    TimetableService timetableService;

    @Autowired
    TimetableRepository timetableRepository;

    @GetMapping("/timetables")
    public Flux<Timetable> getAll() {
        return timetableService.findAll();
    }

    @GetMapping("/timetable")
    public Mono<Timetable> getBySchoolClass(@RequestParam("schoolCode") String schoolCode, @RequestParam("schoolClass") String schoolClass) {
        return timetableService.findBySchoolCodeAndSchoolClass(schoolCode, schoolClass);
    }

    @PostMapping("/timetable")
    public Mono insertTimetable() {
        Timetable timetable = new Timetable(new ObjectId().toString(), "2-2", "7430310", List.of("vbh3hd93id", "29enf3ijdf", "dn2edi3nj8edj", "asdnfniune1213"));
        return timetableService.insertTimetable(timetable);
    }

}
