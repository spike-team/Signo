package kim.jaehoon.studentable.signo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teacher")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    private ObjectId id;

    private String email;
    private String password;
    private String schoolCode;
}
