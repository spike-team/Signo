package kim.jaehoon.studentable.signo.domain.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

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
