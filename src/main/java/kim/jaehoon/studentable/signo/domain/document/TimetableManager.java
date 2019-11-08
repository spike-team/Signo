package kim.jaehoon.studentable.signo.domain.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("timetable_manager")
public class TimetableManager {
    @Id
    private ObjectId id;

    private String name;

    private String email;

    private String password;

    private String schoolCode;

    private boolean emailVerified;
}
