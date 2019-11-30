package kim.jaehoon.studentable.signo.domain.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kim.jaehoon.studentable.signo.domain.entity.Subject;
import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MappedTimetable {
    @Id
    @JsonIgnore
    private String id;

    private String schoolClass;

    private String schoolCode;

    private List<Mono<Subject>> subjects;

    public MappedTimetable(Timetable timetable) {
        this.id = timetable.getId();
        this.schoolClass = timetable.getSchoolClass();
        this.schoolCode = timetable.getSchoolCode();
    }
}
