package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.Meal;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;

public interface MealRepository extends CrudRepository<Meal, String> {

    Flux<Meal> findBySchoolIdAndDate(String schoolId, String date);
}
