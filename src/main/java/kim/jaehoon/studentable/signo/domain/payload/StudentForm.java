package kim.jaehoon.studentable.signo.domain.payload;

import kim.jaehoon.studentable.signo.domain.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentForm {

    @Email
    private String email;

    @Valid
    private String deviceToken;

    @Valid
    private String schoolCode;

    @Valid
    private String schoolClass;

    public Student toStudent() {
        return new Student(new ObjectId().toString(), email, deviceToken, schoolCode, schoolClass);
    }

    public Student toStudentWithId(String id) {
        return new Student(id, email, deviceToken, schoolCode, schoolClass);
    }

}
