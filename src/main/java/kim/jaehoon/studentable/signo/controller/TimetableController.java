package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.entity.Changes;
import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import kim.jaehoon.studentable.signo.domain.payload.PreChanges;
import kim.jaehoon.studentable.signo.service.changes.ChangesService;
import kim.jaehoon.studentable.signo.service.timetable.TimetableService;
import kim.jaehoon.studentable.signo.service.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class TimetableController {

    private TokenService tokenService;
    private ChangesService changesService;
    private TimetableService timetableService;

    @GetMapping(value = "/timetable")
    public Mono<Timetable> getBySchoolClass(@RequestParam String schoolCode, @RequestParam String schoolClass) {
        return timetableService.findBySchoolCodeAndSchoolClass(schoolCode, schoolClass);
    }

    @GetMapping(value = "/timetable/excel")
    public Mono generateExcelSheetForSingleTimetable(ServerHttpResponse response, @RequestParam String schoolCode, @RequestParam String schoolClass) {
        return timetableService.generateExcelSheetForSingleTimetable(response, schoolCode, schoolClass);
    }

    @PutMapping(value = "/timetable/excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono importSingleTimetable(@RequestHeader("Authorization") String auth, @RequestPart("excelData") FilePart files, @RequestParam("schoolClass") String schoolClass) {
        String managerEmail = tokenService.getIdentity(auth.replace("Bearer ", ""));
        return timetableService.importExcelSheetForSingleTimetable(managerEmail, schoolClass, files);
    }

    @GetMapping(value = "/timetables")
    public Flux<Timetable> getAll() {
        return timetableService.findAll();
    }

    @GetMapping(value = "/timetables/excel")
    public Mono generateExcelSheetForMultipleTimetable(ServerHttpResponse response, @RequestParam String schoolCode) throws IOException {
        return timetableService.generateExcelSheetForMultipleTimetable(response, schoolCode);
    }

    @PutMapping(value = "/timetables/excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux importMultipleTimetable(@RequestHeader("Authorization") String auth, @RequestPart("excelData") FilePart files) {
        String managerEmail = tokenService.getIdentity(auth.replace("Bearer ", ""));
        return timetableService.importExcelSheetForMultipleTimetable(managerEmail, files);
    }

    @GetMapping(value = "/timetable/changes")
    public Flux<Changes> getChanges(@RequestParam String schoolCode, @RequestParam String schoolClass) {
        return changesService.findAll(schoolCode, schoolClass);
    }

    @PostMapping(value = "/timetable/changes")
    public Mono<Changes> appendChanges(@RequestBody PreChanges prechanges) {
        return changesService.appendChanges(prechanges.toChanges());
    }

}
