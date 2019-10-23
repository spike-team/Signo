package kim.jaehoon.studentable.signo.domain.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter @Setter
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;
}
