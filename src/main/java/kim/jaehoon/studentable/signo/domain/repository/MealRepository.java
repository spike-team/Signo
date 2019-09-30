package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.Meal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MealRepository extends ReactiveMongoRepository<Meal, ObjectId> {

    Flux<Meal> findBySchoolCodeAndDate(String schoolCode, String date);

    Flux<Meal> findAll();
}
