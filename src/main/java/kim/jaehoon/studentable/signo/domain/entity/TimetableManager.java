package kim.jaehoon.studentable.signo.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Data
@Document("timetable_manager")
public class TimetableManager {
    @Id
    private ObjectId id;

    private String name;

    private String email;

    private String password;

    private String schoolCode;
}
