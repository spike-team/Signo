package kim.jaehoon.studentable.signo.service.meal;

import kim.jaehoon.studentable.signo.domain.payload.Menu;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public interface MealService {

    Mono<Menu> findBySchoolAndDate(String schoolCode, LocalDate date);

}
