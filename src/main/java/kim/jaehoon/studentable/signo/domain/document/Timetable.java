package kim.jaehoon.studentable.signo.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Document("timetable")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    private ObjectId id;

    @Field("school_class")
    private int schoolClass;

    private LocalDate date;

    @Field("school_code")
    private String schoolCode;

    private Flux<Subject> subjects;
}
