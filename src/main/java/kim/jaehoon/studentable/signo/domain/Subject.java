package kim.jaehoon.studentable.signo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subject")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    private ObjectId id;

    private String schoolCode;
    private Teacher teacher;
    private int grade;

}
