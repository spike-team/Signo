package kim.jaehoon.studentable.signo.domain.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("timetable")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id @JsonIgnore
    private String id;

    private String schoolClass;

    private String schoolCode;

    private List<String> subjects;
}
