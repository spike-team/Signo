package kim.jaehoon.studentable.signo.domain.repository;

import kim.jaehoon.studentable.signo.domain.entity.Meal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MealRepository extends ReactiveMongoRepository<Meal, ObjectId> {

    Mono<Meal> findBySchoolCodeAndDate(String schoolCode, String date);

}
