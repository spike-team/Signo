package kim.jaehoon.studentable.signo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.TimeToLive;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Changes {

    String schoolCode;
    String schoolClass;

    Integer original;
    Integer modified;

    LocalDateTime changedAt;
    LocalDateTime expiredAt;

}
