package kim.jaehoon.studentable.signo.domain;

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
import java.util.ArrayList;

@Document("time_table")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    private ObjectId id;

    private String schoolCode;
    private int grade;
    private int schoolClass;
    private ArrayList<String> subjects;
    private LocalDate date;
}
