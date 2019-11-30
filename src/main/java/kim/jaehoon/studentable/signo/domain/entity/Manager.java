package kim.jaehoon.studentable.signo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Manager {

    @Id private String id;
    private String email;
    @JsonIgnore private String password;
    private String schoolCode;
    private boolean emailVerified = false;
    private String verificationCode;

    public Manager(String email, String password, String schoolCode) {
        this.id = new ObjectId().toString();
        this.email = email;
        this.password = password;
        this.schoolCode = schoolCode;
    }

    public void generateVerificationCode() {
        this.verificationCode = UUID.randomUUID().toString();
    }

}
