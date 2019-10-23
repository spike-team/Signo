package kim.jaehoon.studentable.signo.service;

import kim.jaehoon.studentable.signo.domain.entity.Meal;
import reactor.core.publisher.Flux;

public interface IMealService {

    void create(Meal meal);

    Flux<Meal> findBySchoolAndDate(String schoolCode, String date);

    Flux<Meal> findAll();
}
