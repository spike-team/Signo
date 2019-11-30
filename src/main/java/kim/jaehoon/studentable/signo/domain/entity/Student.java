package kim.jaehoon.studentable.signo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id @JsonIgnore
    private String id;

    @Email
    private String email;

    private String deviceToken;

    private String schoolCode;

    private String schoolClass;

}
