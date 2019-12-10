package kim.jaehoon.studentable.signo.service.timetable;

import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public interface TimetableService {

    Mono<Timetable> findBySchoolCodeAndSchoolClass(String schoolCode, String schoolClass);

    Flux<Timetable> findAll();

    Mono<Timetable> importExcelSheetForSingleTimetable(String email, String schoolClass, FilePart filePart);

    Flux importExcelSheetForMultipleTimetable(String email, FilePart filePart);

    Mono generateExcelSheetForSingleTimetable(ServerHttpResponse response, String schoolCode, String schoolClass);

    Mono generateExcelSheetForMultipleTimetable(ServerHttpResponse response, String schoolCode) throws IOException;

}
