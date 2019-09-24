package kim.jaehoon.studentable.signo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Document("time_table")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    private School school;
    private int grade;
    @Field("school_class")
    private int schoolClass;
    private Flux<Subject> subjects;
    private LocalDate date;
}
