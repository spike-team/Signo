package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import kim.jaehoon.studentable.signo.domain.payload.MappedTimetable;
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

    private TimetableService timetableService;
    private TimetableRepository timetableRepository;

    @Autowired
    public TimetableController(TimetableService timetableService, TimetableRepository timetableRepository) {
        this.timetableService = timetableService;
        this.timetableRepository = timetableRepository;
    }

    @GetMapping("/timetables")
    public Flux<Timetable> getAll() {
        return timetableService.findAll();
    }

    @GetMapping("/timetable")
    public Mono<MappedTimetable> getBySchoolClass(@RequestParam String schoolCode, @RequestParam("schoolClass") String schoolClass) {
        return timetableService.findBySchoolCodeAndSchoolClass(schoolCode, schoolClass);
    }

}
