package kim.jaehoon.studentable.signo.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teachers {
    private List<TeacherInfo> teachers;
}
