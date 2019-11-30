package kim.jaehoon.studentable.signo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.DayOfWeek;
import java.util.List;

@Document("subject")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    private String id;

    @Field("school_code")
    private String schoolCode;

    @Field("school_class")
    private List<String> schoolClass;

    @Field("teacher_id")
    private String teacherId;

    private String name;

    private List<DayOfWeek> dayOfWeek;

    @Field("class_days")
    private Integer classDays;

}
