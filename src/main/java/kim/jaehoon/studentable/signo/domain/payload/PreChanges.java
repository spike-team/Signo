package kim.jaehoon.studentable.signo.domain.payload;

import kim.jaehoon.studentable.signo.domain.entity.Changes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreChanges {

    String schoolCode;
    String schoolClass;
    Integer original;
    Integer modified;

    public Changes toChanges() {
        return new Changes(schoolCode, schoolClass, original, modified, LocalDateTime.now(), LocalDateTime.now().plusWeeks(1));
    }

}
