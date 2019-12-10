package kim.jaehoon.studentable.signo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("school")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolInfo {

    @Id
    @JsonIgnore
    private String id;

    @Field("school_code")
    private String schoolCode;

    @Field("full_name")
    private String fullName;

    private String name;

}
