package kim.jaehoon.studentable.signo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("teacher")
@AllArgsConstructor
public class Teacher {

    @Id
    private String id;

    @Field("school_code")
    private String schoolCode;

    private String name;

}
