package kim.jaehoon.studentable.signo.domain.payload;

import kim.jaehoon.studentable.signo.domain.document.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInfo {
    private String name;
    private String assignedTask;

    public Teacher toTeacher(String schoolCode) {
        return new Teacher(new ObjectId().toString(), schoolCode, name);
    }
}
