package kim.jaehoon.studentable.signo.domain.payload;

import kim.jaehoon.studentable.signo.domain.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.DayOfWeek;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreSubject {
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

    public Subject toSubject() {
        return new Subject(new ObjectId().toString(), schoolCode, schoolClass, teacherId, name, dayOfWeek, classDays);
    }

}
