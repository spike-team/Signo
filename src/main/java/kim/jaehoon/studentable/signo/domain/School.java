package kim.jaehoon.studentable.signo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("school")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class School {

    private String schoolId;
    private String name;
}
