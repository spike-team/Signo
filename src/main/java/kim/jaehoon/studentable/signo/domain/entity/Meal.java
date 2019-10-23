package kim.jaehoon.studentable.signo.domain.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.core.publisher.Flux;
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

    @Field("school_code")
    private String schoolCode;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String date;

    @Field("meal_type")
    private String mealType;

    private Flux<String> menu;
}
