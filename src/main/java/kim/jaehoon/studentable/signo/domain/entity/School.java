package kim.jaehoon.studentable.signo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("school")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class School {

    @Id
    private ObjectId id;

    @Field("school_code")
    private String schoolCode;
    private String name;
}
