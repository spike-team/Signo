package kim.jaehoon.studentable.signo.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;

@Document("meal")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    private ObjectId id;

    private String schoolCode;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String date;

    private String mealType;

    private ArrayList<String> menu;
}
