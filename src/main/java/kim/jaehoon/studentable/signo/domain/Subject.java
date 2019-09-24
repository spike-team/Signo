package kim.jaehoon.studentable.signo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subject")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    private School school;
    private Teacher teacher;
    private int grade;

}
