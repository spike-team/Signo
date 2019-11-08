package kim.jaehoon.studentable.signo.domain.document;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("teacher")
public class Teacher {

    @Id
    private ObjectId id;

    private String name;

    @Field("school_code")
    private String schoolCode;
}
