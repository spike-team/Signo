package kim.jaehoon.studentable.signo.domain.entity;

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

    private String schoolCode;

    private String schoolClass;

    private List<String> subjects;
}
