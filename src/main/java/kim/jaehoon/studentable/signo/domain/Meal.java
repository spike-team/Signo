package kim.jaehoon.studentable.signo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Document("meal")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    private School school;
    private String date;
    @Field("meal_type")
    private String mealType;
    private ArrayList<String> menu;
}
