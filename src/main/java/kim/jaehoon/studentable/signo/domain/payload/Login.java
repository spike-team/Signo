package kim.jaehoon.studentable.signo.domain.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;
}
