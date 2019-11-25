package kim.jaehoon.studentable.signo.domain.payload;

import kim.jaehoon.studentable.signo.domain.document.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

    @Email
    private String email;

    private String password;

    private String schoolCode;

    public Manager toManager() {
        return new Manager(email, password, schoolCode);
    }

}
