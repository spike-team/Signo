package kim.jaehoon.studentable.signo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teacher")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    private String email;
    private String password;
    private School school;
}
