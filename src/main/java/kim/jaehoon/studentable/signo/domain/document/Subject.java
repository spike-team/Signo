package kim.jaehoon.studentable.signo.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
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
    private ObjectId id;

    @Field("teacher_id")
    private String teacherId;

    @Field("school_code")
    private String schoolCode;

    @Field("school_class")
    private String schoolClass;

    private String name;

    private DayOfWeek dayOfWeek;

    @Field("class_days")
    private Integer classDays;

}
