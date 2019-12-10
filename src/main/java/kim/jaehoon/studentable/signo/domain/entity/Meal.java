package kim.jaehoon.studentable.signo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kim.jaehoon.studentable.signo.domain.payload.Menu;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Document("meal")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    @JsonIgnore
    private ObjectId id;

    @JsonIgnore
    @Field("school_code")
    private String schoolCode;

    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String date;

    private List<Menu> menus;
}
