package kim.jaehoon.studentable.signo.domain.payload;

import kim.jaehoon.studentable.signo.domain.document.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new Manager(email, encoder.encode(password), schoolCode);
    }

}
