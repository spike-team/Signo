package kim.jaehoon.studentable.signo.service.meal;

import kim.jaehoon.studentable.signo.domain.document.Meal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public interface MealService {

    Mono<Meal> findBySchoolAndDate(String schoolCode, LocalDate date);

}
